# 使用

```
mvn clean package

docker build -t local_test/spring-boot-docker:1.0-SNAPSHOT .

docker run -p 8080:8080 --name spring-boot-docker -d local_test/spring-boot-docker:1.0-SNAPSHOT 

```

# Dockerfile说明

- FROM 指定基础镜像
- RUN 执行命令，可以执行shell命令和可执行文件：
  - shell命令格式：`RUN <命令>`
  - 可执行文件：`RUN ["executable","param1","param2"]`
- COPY 复制文件，格式：`COPY <src> <dest>`从src复制到文件到dest
- EXPOSE 指定容器在运行时监听的端口

# 