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

- `FROM`：指定基础镜像
- `RUN`：执行命令，在`docker build`时运行，常用来在构建镜像时安装应用和软件包，有两种形式：
  - shell：`RUN command param1`，执行的时候使用sh程序的子程序执行：`/bin/sh -c 'command param1'`
  - exec：`RUN ["executable","param1","param2"]`，推荐使用这种
- `CMD`：设置容器启动后默认执行的命令和参数，这些命令能够被`docker run`命令后面的命令行参数替换，也就是如果`docker run`后面有指定命令参数，则`CMD`中的命令不会被执行。一个Dockerfile中只能有一个`CMD`，如果有多个则只有最后一个生效。有三种形式：
  - shell：`CMD command param1 param2`
  - exec：`CMD ["executable","param1","param2]`，推荐使用这种
  - `CMD ["param1","param2"]`，这种形式和`ENTRYPOINT`一起使用，为`ENTRYPOINT`提供额外参数
- `ENTRYPOINT`：设置容器启动时要执行的命令和参数，可使用`docker run --entrypoint`选项覆盖`ENTRYPOINT`中执行的命令.一般变参使用`CMD`，不变的使用`ENTRYPIONT`，两者配合使用。有两种形式：
  - shell：`ENTRYPOINT command param1 param2`
  - exec：`ENTRYPOINT ["executable", "param1", "param2"]`
- `LABEL`：给镜像添加一些元数据，键值对形式：`LABEL <key>=<value> <key>=<value> <key>=<value> ...`
- `EXPOSE`：声明容器在运行时监听的端口，仅仅是声明，使用：`EXPOSE <port> [<port>/<protocol>...]`
- `ENV`：设置容器内的环境变量，使用：`ENV <key>=<value>`
- `COPY`：复制文件，将构建上下文的文件复制到镜像中去，格式：`COPY <src> <dest>`从src复制到文件到dest，有两种形式：
  - `COPY [--chown=<user>:<group>] <src>... <dest>`
  - `COPY [--chown=<user>:<group>] ["<src>",... "<dest>"]`
- `ADD`：复制文件，比`COPY`更高级，可以支持从网络地址上下载文件、可以支持自动解压文件，有两种形式：
  - `ADD [--chown=<user>:<group>] <src>... <dest>`
  - `ADD [--chown=<user>:<group>] ["<src>",... "<dest>"]`
- `VOLUME`：在镜像中创建挂载点，使用：`VOLUME ["/data"]`
- `WORKDIR`：设置工作目录，使用：`WORKDIR /path/to/workdir`
- `ARG`：构建参数，和`ENV`类似，但是`ARG`设置的普通变量不会保存到构建的镜像中
- `HEALTHCHECK`：健康检查
- `SHELL`：可以指定`RUN`、`ENTRYPOINT`、`CMD`等指令执行时候使用的shell，默认是：`["/bin/sh", "-c"]`


# docker命令

- `docker build`：用Dockerfile来构建一个镜像
- `docker container`：管理容器
- `docker cp`：在容器和本机之间进行相互拷贝
- `docker exec`：在正在运行的容器中执行命令
- `docker history`：显示镜像的历史
- `docker image`：管理镜像
- `docker images`：列出镜像
- `docker info`：显示系统信息
- `docker logs`：获取容器的日志
- `docker network`：管理网络
- `docker port`：列出容器的端口映射或者设置容器的端口映射
- `docker ps`：列出容器
- `docker pull`：从仓库拉取镜像
- `docker push`：向仓库推送镜像
- `docker rename`：重命名容器
- `docker restart`：重启一个或多个容器
- `docker rm`：移除一个或者多个容器
- `docker rmi`：移除一个或多个镜像
- `docker run`：运行容器
- `docker start`：启动一个或多个容器
- `docker stop`：停止一个或多个容器
- `docker tag`：给镜像创建一个标签
- `docker top`：查看一个容器中正在运行的进程
- `docker version`：显示Docker版本信息
- `docker volume`：管理卷

## docker build

`docker build`命令格式如下：

```
docker build [OPTIONS] PATH | URL | -
```

`OPTIONS`有如下：

- `--file`或`-f`：可指定Dockerfile
- `--tag`或`-t`：加标签

## docker cp

`docker cp`命令格式如下：

```
docker cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH|-

docker cp [OPTIONS] SRC_PATH|- CONTAINER:DEST_PATH
```

## docker exec

`docker exec`命令格式如下：

```
docker exec [OPTIONS] CONTAINER COMMAND [ARG...]
```

`OPTIONS`有如下：

- `--detach`或者`-d`：后台运行命令
- `--interactive`或者`-i`：让命令执行结果返回
- `--tty`或者`-t`：分配一个伪终端

使用示例，对容器执行bash：`docker exec -it 容器 /bin/bash`

## docker image

### docker image build

镜像构建

`docker image build`命令格式如下：

```
docker image build [OPTIONS] PATH | URL | -
```

`OPTIONS`有如下：

- `--file`或者`-f`：指定Dockerfile
- `--tag`或者`-t`：加标签

### docker image history

查看镜像历史

`docker image history`命令格式如下：

```
docker image history [OPTIONS] IMAGE
```

### docker image ls

列出镜像

`docker image ls`命令格式如下：

```
docker image ls [OPTIONS] [REPOSITORY[:TAG]]
```

`OPTIONS`有如下：

- `--all`或者`-a`：显示全部镜像，默认不显示中间状态的镜像

### docker image rm

移除镜像

`docker image rm`命令格式如下：

```
docker image rm [OPTIONS] IMAGE [IMAGE...]
```

`OPTIONS`有如下：

- `--force`或者`-f`：强制移除镜像

## docker logs

查看日志

`docker logs`命令格式如下：

```
docker logs [OPTIONS] CONTAINER
```

`OPTIONS`有如下：

- `--follow`或者`-f`：显示最新的日志
- `--tail`或者`-t`：指定显示的日志的行数

## docker network

### docker network connect

将容器连接到指定的网络上

`docker network connect`命令格式如下：

```
docker network connect [OPTIONS] NETWORK CONTAINER
```

### docker network create

`docker network create [OPTIONS] NETWORK`

### docker network disconnect

将容器从指定的网络上断开

`docker network disconnect`命令格式如下：

```
docker network disconnect [OPTIONS] NETWORK CONTAINER
```

### docker network ls

列出网络

`docker network ls`命令格式如下：

```
docker network ls [OPTIONS]
```

### docker network rm

移除网络

`docker network rm`命令格式如下：

```
docker network rm NETWORK [NETWORK...]
```

## docker port

列出容器的软口映射或者给容器指定端口映射

`docker port`命令格式如下：

```
docker port CONTAINER [PRIVATE_PORT[/PROTO]]
```

## docker ps

列出容器

`docker ps`命令格式如下：

```
docker ps [OPTIONS]
```

`OPTIONS`有如下：

- `--all`或者`-a`：显示所有容器，默认只显示运行中的

## docker pull

拉取镜像

`docker pull`命令格式如下：

```
docker pull [OPTIONS] NAME[:TAG|@DIGEST]
```

## docker push

推送镜像

`docker push`命令格式如下：

```
docker push [OPTIONS] NAME[:TAG]
```

## docker rename

重命名容器

`docker rename`命令格式如下：

```
docker rename CONTAINER NEW_NAME
```

## docker restart

重启一个或者多个容器

`docker restart`命令格式如下：

```
docker restart [OPTIONS] CONTAINER [CONTAINER...]
```

## docker rm

删除一个或者多个容器

`docker rm`命令格式如下：

```
docker rm [OPTIONS] CONTAINER [CONTAINER...]
```

`OPTIONS`有如下：

- `--force`或者`-f`：强制删除一个正在运行的容器
- `--volumes`或者`-v`：移除容器关联的卷

## docker rmi

删除一个或者多个镜像

`docker rmi`命令格式如下：

```
docker rmi [OPTIONS] IMAGE [IMAGE...]
```

`OPTIONS`有如下：

- `--force`或者`-f`：强制删除镜像

## docker run

`docker run`命令格式如下：

```
docker run [OPTIONS] IMAGE[:TAG|@DIGEST] [COMMAND] [ARG...]
```

`OPTIONS`有如下：

- `-d` 或者`-d=true`：detached模式，让容器在后台运行，比如：`docker run -d -p 8080:8080 local_test/spring-boot-docker-app:1.0.0-SNAPSHOT`
- `--name`：为容器指定一个名字，如果不指定名字，docker会默认生成一个UUID给容器作为名字，比如：`docker run -d -p 8080:8080 --name spring-boot-docker-app local_test/spring-boot-docker-app:1.0.0-SNAPSHOT`
- `--network`或者`--net`：为容器指定网络，比如：`docker run -d -p 8080:8080 --network spring_boot_docker_app_network local_test/spring-boot-docker-app:1.0.0-SNAPSHOT`
- `--volume`或者`-v`：绑定挂载卷
- `--workdir`或者`-w`：指定容器内的工作目录
- `--rm`：容器退出时自动清理内部文件系统
- `--link`：连接到指定的容器

`IMAGE[:TAG]`中的`TAG`可以为要启动的容器指定一个标签，标签可以是版本号，比如：`docker run -d -p 8080:8080 local_test/spring-boot-docker-app:1.0.0-SNAPSHOT`

## docker start

启动一个或者多个容器

`docker start`命令格式如下：

```
docker start [OPTIONS] CONTAINER [CONTAINER...]
```

## docker stop

停止一个或者多个容器

`docker stop`命令格式如下：

```
docker stop [OPTIONS] CONTAINER [CONTAINER...]
```

## docker tag

创建一个tag

`docker tag`命令格式如下：

```
docker tag SOURCE_IMAGE[:TAG] TARGET_IMAGE [:TAG]
```

## docker top

查看一个容器中正在运行的进程

`docker top`命令格式如下：

```
docker top CONTAINER[ps OPTIONS]
```

# docker-compose/docker compose

`docker-compose`是旧版本的命令，`docker compose`是新版本的命令，使用方式如下：

```
 docker compose [OPTIONS] COMMAND
```


`OPTIONS`有如下：

- `--file`或者`-f`：指定compose file，默认是`docker-compose.yml`
- `--project-name`或者`-p`：指定项目名字，默认是目录的名字
- `--profile`：指定启用的profile

`COMMAND`有如下：

- `docker compose build`：构建或者重新构建服务
- `docker compose config`：校验并显示配置文件
- `docker compose create`：创建服务
- `docker compose down`：停止并移除容器、网络、镜像、卷
- `docker compose images`：列出镜像
- `docker compose kill`：kill掉容器
- `docker compose pause`：暂停服务
- `docker compose ps`：列出容器
- `docker compose pull`：拉取服务镜像
- `docker compose push`：推送服务镜像
- `docker compose restart`：重启服务
- `docker compose rm`：移除已经停止的容器
- `docker compose start`：启动服务
- `docker compose stop`：停止服务
- `docker compose top`：显示运行中的进行
- `docker compose unpause`：恢复暂停中的服务
- `docker compose up`：创建并启动容器

# docker-compose.yml文件

## 示例

```yaml
# 指定版本
version: "3.8"
# services定义服务，定义了该服务启动的每个容器的配置
services:

  # 服务名称
  redis:
    # 启动容器的镜像
    image: redis:alpine
    ports:
      - "6379"
    networks:
      - frontend
    # 指定部署和运行服务的配置，仅在swam mode下生效，并且只能通过docker stack deploy命令部署，
    # docker-compose up和docker-compose run命令将被忽略
    deploy:
      replicas: 2
      update_config:
        parallelism: 2
        delay: 10s
      restart_policy:
        condition: on-failure

  db:
    image: postgres:9.4
    # volumes定义卷
    volumes:
      - db-data:/var/lib/postgresql/data
    networks:
      - backend
    deploy:
      placement:
        constraints:
          - "node.role==manager"

  vote:
    image: dockersamples/examplevotingapp_vote:before
    ports:
      - "5000:80"
    networks:
      - frontend
    # 指定服务间的依赖关系，解决服务启动的先后顺序问题
    # 这里是当前服务依赖redis服务，需要先启动redis服务，停止当前服务前也会先停止redis服务
    # 服务不会等待该服务所依赖的服务完全启动之后才启动
    depends_on:
      - redis
    deploy:
      replicas: 2
      update_config:
        parallelism: 2
      restart_policy:
        condition: on-failure

  result:
    image: dockersamples/examplevotingapp_result:before
    ports:
      - "5001:80"
    networks:
      - backend
    depends_on:
      - db
    deploy:
      replicas: 1
      update_config:
        parallelism: 2
        delay: 10s
      restart_policy:
        condition: on-failure

  worker:
    image: dockersamples/examplevotingapp_worker
    networks:
      - frontend
      - backend
    deploy:
      mode: replicated
      replicas: 1
      # 指定服务的标签，仅在服务上设置，不在服务的任何容器上设置
      labels: [APP=VOTING]
      restart_policy:
        condition: on-failure
        delay: 10s
        max_attempts: 3
        window: 120s
      placement:
        constraints:
          - "node.role==manager"

  visualizer:
    image: dockersamples/visualizer:stable
    ports:
      - "8080:8080"
    stop_grace_period: 1m30s
    volumes:
      - "/var/run/docker.sock:/var/run/docker.sock"
    deploy:
      placement:
        constraints:
          - "node.role==manager"
# networks定义网络
networks:
  frontend:
  backend:

# volumes定义卷
volumes:
  db-data:
```

## 详解

```yaml
# 指定版本
version: "3.8"
# services定义服务，定义了该服务启动的每个容器的配置
services:

  # 服务名称
  service1:
    # 启动容器的镜像
    image: service1:alpine
    # 指定容器名称
    # Docker容器的名称必须唯一，所以为一个服务指定了自定义容器名称后，该服务不能进行扩展，如果尝试为该服务扩容将会导致错误
    container_name: service-one
    # 暴露端口，可以使用HOST:CONTAINER的格式指定端口映射，也可以指定容器端口，宿主机会随机选择临时端口进行映射
    ports:
      - "8080:8080
    # 指定所加入的网络，需要在顶层networks配置项中引入具体的网络信息
    networks:
      - frontend
    # 覆盖容器启动后默认执行的命令
    command:
      - echo "hello world"
    # 覆盖默认的入口命令
    entrypoint: /code/entrypoint.sh
    # 暴露指定端口，但不映射到宿主机，只被连接的服务访问。只能指定内部端口。
    expose:
      - "3000"
    # 配置运行检查以确定服务容器是否健康
    healthcheck:
      # 指定健康检测的方法
      test: ["CMD", "curl", "-f", "http://localhost"]
      # 启动容器到进行健康检查的间隔时间以及两次健康检查的间隔时间
      interval: 1m30s
      # 单次健康检查的超时时间，超过该时间该次健康检查失败
      timeout: 3s
      # 健康检查失败后的最大重试次数，重试了最大次数依然失败，容器将被视为unhealthy
      retries: 3
      # 为需要时间引导的容器提供的初始化时间，在此期间检查失败将不计入最大重试次数，但是如果在启动期间健康检查成功，则会将容器视为已启动，并且所有连续失败将计入最大重试次数。
      start_period: 40s
    # 将元数据以标签的形式添加到容器中。可以使用数组或字典两种格式
    # com.example.description: "Accounting webapp"
    # - "com.example.description=Accounting webapp"
    labels:
      com.example.description: "Accounting webapp"
    # 为每个服务授权对configs的访问权限
    configs:
      # 指定config名称
      - source: my_config
        # 挂载到service1服务的容器的路径以及名称
        target: /service1_config
        # 指定uid
        uid: '103'
        # 指定gid
        gid: '103'
        # 挂载到容器的文件权限
        mode: 0440
      # 指定config名称
      - my_other_config
    # 指定服务间的依赖关系，解决服务启动的先后顺序问题
    # 这里是当前服务依赖some_other_service服务，需要先启动some_other_service服务，停止当前服务前也会先停止some_other_service服务
    # 服务不会等待该服务所依赖的服务完全启动之后才启动
    depends_on:
      - some_other_service
    # 指定部署和运行服务的配置，仅在swam mode下生效，并且只能通过docker stack deploy命令部署，
    # docker-compose up和docker-compose run命令将被忽略
    deploy:
      # 指定服务的标签，仅在服务上设置，不在服务的任何容器上设置
      labels: [app=123]
      # 指定服务的容器副本模式
      # global：每个swarm节点只有一个该服务容器
      # replicated：整个集群中存在指定份数的服务容器副本，为默认值
      mode: replicated
      # 如果服务的容器副本模式为replicated（默认），指定运行的容器副本数量
      replicas: 2
      # 配置资源限制
      # 如下指定：服务使用的cpu份额为25%到50%，内存为20M到50M
      resources:
        limits:
          cpus: '0.50'
          memory: 50M
        reservations:
          cpus: '0.25'
          memory: 20M
      # 配置如何更新服务。该配置对滚动更新很有用。
      update_config:
        # 一次更新的容器数量
        parallelism: 2
        # 更新一组容器之间的等待时间
        delay: 10s
      # 指定容器的重启策略
      restart_policy:
        # 重启策略。值可以为none、on-failure或any，默认为any
        condition: on-failure
        # 尝试重启的等待时间
        delay: 5s
        # 重启最多尝试的次数，超过该次数将放弃。默认为永不放弃。
        max_attempts: 10
        # 在决定重启是否成功之前的等待时间，默认立即
        window: 120s
      

  service2:
    image: dockersamples/examplevotingapp_vote:before
    ports:
      - "5000:80"
    networks:
      - frontend
    # 指定服务间的依赖关系，解决服务启动的先后顺序问题
    # 这里是当前服务依赖service1服务，需要先启动service1服务，停止当前服务前也会先停止service1服务
    # 服务不会等待该服务所依赖的服务完全启动之后才启动
    depends_on:
      - service1
    deploy:
      replicas: 2
      update_config:
        parallelism: 2
      restart_policy:
        condition: on-failure
# networks定义网络
networks:
  frontend:
  backend:

# volumes定义卷
volumes:
  db-data:

# 定义配置
configs:
  my_config: 
    file: ./my_config.json
  my_other_config:
    external: true
```