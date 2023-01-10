# spring-cloud-gateway-server-redis

# 使用Docker安装redis并启动

- 拉取最新镜像：`docker pull redis:6.2.7`
- 运行容器：`docker run -itd --name redis -p 6379:6379 redis:6.2.7`
- 使用`redis-cli`连接redis服务并测试，先进入bash：`docker exec -it redis /bin/bash`，然后输入命令：`redis-cli`

# 使用

获取路由信息：`http://localhost:8080/actuator/gateway/routes`

新增路由配置：

```
POST http://localhost:8080/actuator/gateway/routes/spring-cloud-gateway-service-1

{
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
  }
```

这种直接调用actuator接口的方式，能更新redis中的数据，但是如果网关是集群部署则集群中其他的机器不能感知到路由的变化，这样就需要使用其他的方式来通知整个集群中的网关服务，可以使用MQ的方式，也可以使用Redis的发布订阅方式，在服务器接收到消息后刷新网关的本地配置信息即可。
