# spring-boot-actuator-datasource

# 使用docker安装启动mysql

- 拉取最新镜像：`docker pull mysql:8.0.32`
- 运行容器：`docker run -itd --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345678 -e MYSQL_DATABASE=spring_boot_actuator_datasource mysql:8.0.32`

# 使用

- 启动项目
- 访问：`http://127.0.0.1:8080/actuator`

# 查看数据源信息

查看数据源配置：`http://127.0.0.1:8080/actuator/configprops/spring.datasource`

## 查看hikaricp信息

- 连接：`http://127.0.0.1:8080/actuator/metrics/hikaricp.connections`

其他可以查看的信息：

- `hikaricp.connections`
- `hikaricp.connections.acquire`
- `hikaricp.connections.active`
- `hikaricp.connections.creation`
- `hikaricp.connections.idle`
- `hikaricp.connections.max`
- `hikaricp.connections.min`
- `hikaricp.connections.pending`
- `hikaricp.connections.timeout`
- `hikaricp.connections.usage`
- `jdbc.connections.max`
- `jdbc.connections.min`,

