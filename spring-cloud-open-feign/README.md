# spring-cloud-open-feign

# open feign性能优化

## 替换默认的Http Client

open feign默认使用JDK自带的HttpURLConnection进行请求，无连接池，需要使用其Apache HttpClient或者OkHttp进行替换。默认的Client为：`feign.Client.Default`，可以通过在`feign.SynchronousMethodHandler.executeAndDecode`方法中打断点查看使用的Client。

### 替换为Apache HttpClient

引入依赖：

```xml
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-hc5</artifactId>
</dependency>
```
配置文件中添加配置开启okhttp client：

```yaml
spring:
  cloud:
    openfeign:
      httpclient:
        hc5:
          # 开启 apache httpclient 5
          enabled: true
```

### 替换为OkHttp

引入依赖：

```xml
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-okhttp</artifactId>
</dependency>
```
配置文件中添加配置开启okhttp client：

```yaml
spring:
  cloud:
    openfeign:
      okhttp:
        # 开启okhttp client
        enabled: true
```

## 设置open feign调用超时时间

### Apache HttpClient设置

```yaml
spring:
  cloud:
    openfeign:
      httpclient:
        # 设置httpclient的连接超时时间，默认是2s
        connection-timeout: 2000
        # 设置连接池最大大小，默认200
        max-connections: 200
        hc5:
          # 开启 apache httpclient 5
          enabled: true
          # 设置连接超时时间，默认3s
          connection-request-timeout: 3
      client:
        config:
          # 单独设置某个服务的配置
          apache-httpclient-server-core:
            # 设置连接超时时间为1s
            connect-timeout: 1000
            # 设置读超时时间为2s
            read-timeout: 2000
```

### okhttp设置

```yaml
spring:
  cloud:
    openfeign:
      okhttp:
        # 开启okhttp client
        enabled: true
      httpclient:
        # 设置httpclient的连接超时时间，默认是2s
        connection-timeout: 2000
        # 设置连接池最大大小，默认200
        max-connections: 200
        ok-http:
          # 设置okhttp的全局读超时时间，默认是60s
          read-timeout: 60000
      client:
        config:
          # 单独设置某个服务的配置
          okhttp-server-core:
            # 设置连接超时时间为1s
            connect-timeout: 1000
            # 设置读超时时间为2s
            read-timeout: 2000
```
