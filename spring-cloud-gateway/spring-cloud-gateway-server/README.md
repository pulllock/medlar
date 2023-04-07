# spring-cloud-gateway-server

# 使用

1. 启动spring-cloud-eureka-server-sample，使用Erueka作为服务注册中心
2. 启动spring-cloud-gateway-server，当前项目作为网关
3. 启动示例服务1和示例服务2：spring-cloud-gateway-service-1、spring-cloud-gateway-service-2
4. 访问`http://localhost:8080/gateway/s1/user/getUserName`和`http://localhost:8080/gateway/s2/order/getOrderInfo`进行验证

# 日志

- 可以添加Java系统属性：`-Dreactor.netty.http.server.accessLogEnabled=true`来启用访问日志
- 自定义日志过滤器

# 鉴权

- 自定义鉴权认证过滤器

# 限流

- 使用Spring Cloud Gateway自带的集群限流
- 自定义限流

## Spring Cloud Gateway自带的集群限流使用

可以使用Spring Cloud Gateway自带的限流，需要自定义KeyResolver，需要依赖Redis，并且是集群限流。下面是使用方式：

使用Docker安装redis并启动：

- 拉取最新镜像：`docker pull redis:6.2.7`
- 运行容器：`docker run -itd --name redis -p 6379:6379 redis:6.2.7`
- 使用`redis-cli`连接redis服务并测试，先进入bash：`docker exec -it redis /bin/bash`，然后输入命令：`redis-cli`

添加redis依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
</dependency>
```

添加redis配置：

```yaml
spring:
  cloud:
    redis:
      host: localhost
      port: 6379
      database: 0
      # password:
      lettuce:
        pool:
          max-active: 8
          max-wait: -1ms
          max-idle: 8
          min-idle: 0
```

自定义KeyResolver实现并注册到容器中：

```java
public class IpKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
```

```java
@Bean
public IpKeyResolver ipKeyResolver() {
    return new IpKeyResolver();
}
```

添加路由配置：

```yaml
spring:
  cloud:
    gateway:
      # default-filters是全局的过滤器配置
      default-filters:
        - name: RequestRateLimiter
          args:
            # 令牌桶每秒填充速率
            redis-rate-limiter.replenishRate: 1
            # 令牌桶总容量
            redis-rate-limiter.burstCapacity: 2
            # 每个请求从桶中取出的令牌数
            redis-rate-limiter.requestedTokens: 1
            # 限流用到的唯一键的解析器，使用SpEL表达式从Spring容器中获取Bean实例
            key-resolver: "#{@ipKeyResolver}"
      routes:
        - id: spring-cloud-gateway-service-1
          uri: lb://spring-cloud-gateway-service-1
          predicates:
            - Path=/s1/**
          filters:
            - StripPrefix=1
            # 这里是针对具体的route的限流配置，可以和全局的同时配置
            - name: RequestRateLimiter
              args:
                # 令牌桶每秒填充速率
                redis-rate-limiter.replenishRate: 1
                # 令牌桶总容量
                redis-rate-limiter.burstCapacity: 2
                # 每个请求从桶中取出的令牌数
                redis-rate-limiter.requestedTokens: 1
                # 限流用到的唯一键的解析器，使用SpEL表达式从Spring容器中获取Bean实例
                key-resolver: "#{@ipKeyResolver}"
```

访问服务如果超过配置的限流阈值，则会返回响应码：429

# 全局异常处理

- 自定义全局异常处理，方式1实现：GatewayErrorAttributes
- 自定义全局异常处理，方式2实现：GlobalErrorExceptionHandler