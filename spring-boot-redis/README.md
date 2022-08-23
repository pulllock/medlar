# 说明

redis示例

# 使用Docker安装redis并启动

- 拉取最新镜像：`docker pull redis:latest`
- 运行容器：`docker run -itd --name redis -p 6379:6379 redis`
- 使用`redis-cli`连接redis服务并测试，先进入bash：`docker exec -it redis /bin/bash`，然后输入命令：`redis-cli`

