# spring-boot-cache-infinispan-remote

# 使用docker安装infinispan

- 拉取镜像：`docker pull infinispan/server:14.0`
- 运行容器：`docker run -d --name infinispan -p 11222:11222 -e USER="admin" -e PASS="12345678" infinispan/server:14.0`
- 访问控制台进行配置：`http://localhost:11222`