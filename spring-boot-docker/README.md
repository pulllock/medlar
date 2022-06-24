# 使用

## docker示例项目：getting-started

运行以下命令来启动示例项目：

```
docker run -d -p 80:80 docker/getting-started
```

命令中的选项如下：

- `-d` 容器运行在detached模式，后台执行
- `-p 80:80` 将主机的80端口映射到容器的80端口
- `docker/getting-started` 容器使用的镜像

命令执行时发生的事情：

1. 如果本地没有`docker/getting-started`这个镜像，会从默认的docker registry上面拉取镜像，相当于手动执行：`docker pull docker/getting-started`
2. docker根据拉取的镜像创建容器，相当于手动执行：`docker container create`
3. docker会为容器申请一个可读写的文件系统
4. docker会为容器创建一个网络接口
5. 启动刚才创建的容器

运行命令后输出的日志如下：

```
Unable to find image 'docker/getting-started:latest' locally
latest: Pulling from docker/getting-started
df9b9388f04a: Downloading
5867cba5fcbd: Download complete
4b639e65cb3b: Download complete
061ed9e2b976: Download complete
bc19f3e8eeb1: Download complete
4071be97c256: Download complete
79b586f1a54b: Download complete
0c9732f525d6: Download complete
latest: Pulling from docker/getting-started
df9b9388f04a: Pull complete
5867cba5fcbd: Pull complete
4b639e65cb3b: Pull complete
061ed9e2b976: Pull complete
bc19f3e8eeb1: Pull complete
4071be97c256: Pull complete
79b586f1a54b: Pull complete
0c9732f525d6: Pull complete
Digest: sha256:b558be874169471bd4e65bd6eac8c303b271a7ee8553ba47481b73b2bf597aae
Status: Downloaded newer image for docker/getting-started:latest
963cecabecc0be0332b772cfaa397ec1b61a1a01b0691258a076129797889042
```

## 创建我们自己的docker应用并运行

1. 创建一个SpringBoot应用：`spring-boot-docker-app`，并编写示例代码
2. 在`spring-boot-docker-app`项目根目录下创建`Dockerfile`文件，文件内容如下：
   ```
   FROM openjdk:8-jre-alpine

   RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories && \
   apk update && \
   mkdir -p /app
   COPY target/spring-boot-docker-app-1.0.0-SNAPSHOT.jar app/spring-boot-docker-app.jar
   EXPOSE 8080
   ENTRYPOINT ["java", "-jar", "app/spring-boot-docker-app.jar"]
   ```
3. 将`spring-boot-docker-app`项目进行打包，执行命令：`mvn clean package`
4. 创建应用的镜像，执行命令：`docker build -t local_test/spring-boot-docker-app:1.0.0-SNAPSHOT .`，命令执行后打印日志如下：
   ```
    [+] Building 10.1s (8/8) FINISHED                                                                                                                                                                                                       
    => [internal] load build definition from Dockerfile                                                                                                                                                                               0.1s
    => => transferring dockerfile: 343B                                                                                                                                                                                               0.1s
    => [internal] load .dockerignore                                                                                                                                                                                                  0.0s
    => => transferring context: 2B                                                                                                                                                                                                    0.0s
    => [internal] load metadata for docker.io/library/openjdk:8-jre-alpine                                                                                                                                                            1.2s
    => CACHED [1/3] FROM docker.io/library/openjdk:8-jre-alpine@sha256:f362b165b870ef129cbe730f29065ff37399c0aa8bcab3e44b51c302938c9193                                                                                               0.0s
    => [internal] load build context                                                                                                                                                                                                  1.5s
    => => transferring context: 17.57MB                                                                                                                                                                                               1.5s
    => [2/3] RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apk/repositories &&     apk update &&     mkdir -p /app                                                                                        8.3s
    => [3/3] COPY target/spring-boot-docker-app-1.0.0-SNAPSHOT.jar spring-boot-docker-app.jar                                                                                                                                                 0.1s
    => exporting to image                                                                                                                                                                                                             0.1s
    => => exporting layers                                                                                                                                                                                                            0.1s
    => => writing image sha256:da48202ab43b07c4d355cbc0c4c511c3112964327c776044069c0aa4e00195ff                                                                                                                                       0.0s
    => => naming to docker.io/local_test/spring-boot-docker-app:1.0.0-SNAPSHOT                                                                                                                                                            0.0s
    
    Use 'docker scan' to run Snyk tests against images to find vulnerabilities and learn how to fix them
   ```
5. 启动应用的容器，执行命令：`docker run -d -p 8080:8080 --name spring-boot-docker-app local_test/spring-boot-docker-app:1.0.0-SNAPSHOT`
6. 启动成功后，访问：`http://localhost:8080`

## 停止我们的docker应用并删除

1. 查看应用的容器id，执行命令：`docker ps -a`，执行命令后可以看到如下输出：
   ```
   CONTAINER ID   IMAGE                                                 COMMAND                  CREATED             STATUS             PORTS                                            NAMES
   782aa60819ff   local_test/spring-boot-docker-app:1.0.0-SNAPSHOT          "java -jar spring-bo…"   21 minutes ago      Up 21 minutes      0.0.0.0:8080->8080/tcp                           spring-boot-docker-app
   ```
   得到应用的id：782aa60819ff
2. 停止容器，执行命令：`docker stop 782aa60819ff`
3. 容器停止后删除容器，执行命令：`docker rm 782aa60819ff`

停止并删除容器还可以使用如下命令执行：`docker rm -f 容器id`

## 持久化/volume操作

volume挂载的目录是docker进行管理的，位于：`/var/lib/docker/volumes`下面

- 创建一个volume：`docker volume create app_dir`
- 启动容器的时候将`app_dir`目录挂载到容器的`/app`目录上：`docker run -d -p 8080:8080 -v app_dir:/app --name spring-boot-docker-app local_test/spring-boot-docker-app:1.0.0-SNAPSHOT`

可以使用`docker volume inspect app_dir`查看刚才创建的volume：
```
[
    {
        "CreatedAt": "2022-06-23T08:59:52Z",
        "Driver": "local",
        "Labels": {},
        "Mountpoint": "/var/lib/docker/volumes/app_dir/_data",
        "Name": "app_dir",
        "Options": {},
        "Scope": "local"
    }
]
```

## 持久化/bind amount

bind amount可以将主机上的任意目录挂载到容器上，假如当前主机上有个`/tmp/app_data`目录，将`/tmp/app_data`目录挂载到容器上，执行如下命令即可：

```
docker run -d -p 8080:8080 -v /tmp/app_data:/app_data --name spring-boot-docker-app local_test/spring-boot-docker-app:1.0.0-SNAPSHOT
```

## network

可以创建一个网络，让多个容器之间进行通信，创建网络：

```
docker network create spring_boot_docker_app_network
```

使用该命令创建了一个名字为spring_boot_docker_network的网络，接下来可以在启动容器的时候指定网络：

```
docker run -d -p 8080:8080 --network spring_boot_docker_app_network -v /tmp/app_data:/app_data --name spring-boot-docker-app local_test/spring-boot-docker-app:1.0.0-SNAPSHOT
```

可以使用`--network-alias xxx`给容器指定一个网络别名，其他容器可以通过该名字访问当前容器

## 使用docker-compose

1. 创建应用`spring-boot-docker-compose-server`，并创建镜像：`docker build -t local_test/spring-boot-docker-compose-server:1.0.0-SNAPSHOT .`
2. 创建应用`spring-boot-docker-compose-client`，并创建镜像：`docker build -t local_test/spring-boot-docker-compose-client:1.0.0-SNAPSHOT .`
3. 创建文件`docker-compose.yml`，内容如下：
   ```
    version: "3.7"

    services:
      spring-boot-docker-compose-server:
      image: local_test/spring-boot-docker-compose-server:1.0.0-SNAPSHOT
      ports:
       - 8081:8081

      spring-boot-docker-compose-client:
      image: local_test/spring-boot-docker-compose-client:1.0.0-SNAPSHOT
      ports:
        - 8082:8082
   ```
4. 启动服务，执行命令：`docker-compose up -d`
5. 查看日志：`docker-compose logs -f`
6. 访问`spring-boot-docker-compose-client`的接口http://localhost:8082/user/queryById?id=1，可以正确的调用`spring-boot-docker-compose-server`服务的接口并返回正确的值
7. 停止并删除容器：`docker-compose down`

# Dockerfile说明

- FROM 指定基础镜像
- RUN 执行命令，可以执行shell命令和可执行文件：
  - shell命令格式：`RUN <命令>`
  - 可执行文件：`RUN ["executable","param1","param2"]`
- COPY 复制文件，格式：`COPY <src> <dest>`从src复制到文件到dest
- EXPOSE 指定容器在运行时监听的端口

# docker命令

## docker run

`docker run`命令格式如下：

```
docker run [OPTIONS] IMAGE[:TAG|@DIGEST] [COMMAND] [ARG...]
```