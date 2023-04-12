# spring-cloud-open-feign

# open feign性能优化

## 替换默认的Http Client

open feign默认使用JDK自带的HttpURLConnection进行请求，无连接池，需要使用其Apache HttpClient或者OkHttp进行替换。默认的Client为：`feign.Client.Default`，可以通过在`feign.SynchronousMethodHandler.executeAndDecode`方法中打断点查看使用的Client。

### 替换为Apache HttpClient

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


