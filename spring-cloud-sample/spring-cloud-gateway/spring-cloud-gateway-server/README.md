# spring-cloud-gateway-server

# 使用

1. 启动spring-cloud-eureka-server-sample，使用Erueka作为服务注册中心
2. 启动spring-cloud-gateway-server，当前项目作为网关
3. 启动示例服务1和示例服务2：spring-cloud-gateway-service-1、spring-cloud-gateway-service-2
4. 访问`http://localhost:8080/gateway/s1/user/getUserName`和`http://localhost:8080/gateway/s2/order/getOrderInfo`进行验证

# 日志

- 可以添加Java系统属性：`-Dreactor.netty.http.server.accessLogEnabled=true`来启用访问日志
- 自定义日志过滤器