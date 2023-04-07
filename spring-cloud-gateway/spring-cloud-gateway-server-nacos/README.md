# spring-cloud-gateway-server-nacos

使用nacos来实现网关动态路由配置

# Docker安装启动nacos server

本地安装启动nacos server：

```
docker pull nacos/nacos-server:v2.2.0
docker run --name nacos-server -e MODE=standalone -p 8848:8848 -p 9848:9848 -p 9849:9849 -it nacos/nacos-server:v2.2.0
```

访问nacos server控制台：`http://localhost:8848/nacos`

# 配置Nacos

创建命名空间：dev

在dev命名空间下创建项目启动配置：

Data ID：spring-cloud-gateway-server.yaml
Group：DEFAULT_GROUP
配置格式：YAML
配置内容：

```yaml
server:
  port: 8080

spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true


eureka:
  instance:
    instance-id: spring-cloud-gateway-server
    hostname: spring-cloud-gateway-server
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: http://localhost:8761/eureka

management:
  endpoint:
    gateway:
      enabled:
  endpoints:
    web:
      exposure:
        include: gateway
```

在dev命名空间下创建网关路由配置：

这种方式是将网关路由配置到单独的配置文件中，代码中实现动态刷新逻辑。也可以不使用这种方式，直接将网关路由配置按照yaml格式配到上面的应用配置中，这样也可以实现动态刷新。

Data ID：gateway-dynamic-route
Group：DEFAULT_GROUP
配置格式：JSON
配置内容：

```json
[
  {
    "id": "spring-cloud-gateway-service-1",
    "uri": "lb://spring-cloud-gateway-service-1",
    "predicates": [
      {
        "name": "Path",
        "args": {
          "pattern": "/gateway/s1/**"
        }
      }
    ],
    "filters": [
      {
        "name": "StripPrefix",
        "args": {
          "_genkey_0": "2"
        }
      }
    ]
  },
  {
    "id": "spring-cloud-gateway-service-2",
    "uri": "lb://spring-cloud-gateway-service-2",
    "predicates": [
      {
        "name": "Path",
        "args": {
          "pattern": "/gateway/s2/**"
        }
      }
    ],
    "filters": [
      {
        "name": "StripPrefix",
        "args": {
          "_genkey_0": "2"
        }
      }
    ]
  }
]
```