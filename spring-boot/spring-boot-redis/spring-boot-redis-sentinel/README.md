# redis sentinel

搭建sentinel模式的Redis集群，1主，2从，3哨兵：

- master: 1
- slave: 2
- sentinel: 3

# 使用docker搭建

使用docker搭建sentinel模式的集群，这种方式我的本地应用不能连到sentinel，暂时没有继续找解决办法。

创建`docker-compose.yml`文件，内容如下：

```yaml
version: "3.7"

services:
  master:
    image: redis:6.2.7
    container_name: redis-master
    restart: always
    # --requirepass 12345678
    command: redis-server --appendonly yes
    ports:
      - 6379:6379
    networks:
      - local_redis_sentinel_net

  slave1:
    image: redis:6.2.7
    container_name: redis-slave-1
    restart: always
    # --requirepass 12345678 --masterauth 12345678
    command: redis-server --slaveof redis-master 6379 --appendonly yes
    ports:
      - 6380:6379
    networks:
      - local_redis_sentinel_net

  slave2:
    image: redis:6.2.7
    container_name: redis-slave-2
    restart: always
    # --requirepass 12345678 --masterauth 12345678
    command: redis-server --slaveof redis-master 6379 --appendonly yes
    ports:
      - 6381:6379
    networks:
      - local_redis_sentinel_net

  sentinel1:
    image: redis:6.2.7
    container_name: redis-sentinel-1
    restart: always
    ports:
      - 26379:26379
    networks:
      - local_redis_sentinel_net
    command: redis-sentinel /usr/local/etc/redis/sentinel.conf
    volumes:
      - ./sentinel1.conf:/usr/local/etc/redis/sentinel.conf

  sentinel2:
    image: redis:6.2.7
    container_name: redis-sentinel-2
    restart: always
    ports:
      - 26380:26379
    networks:
      - local_redis_sentinel_net
    command: redis-sentinel /usr/local/etc/redis/sentinel.conf
    volumes:
      - ./sentinel2.conf:/usr/local/etc/redis/sentinel.conf

  sentinel3:
    image: redis:6.2.7
    container_name: redis-sentinel-3
    restart: always
    ports:
      - 26381:26379
    networks:
      - local_redis_sentinel_net
    command: redis-sentinel /usr/local/etc/redis/sentinel.conf
    volumes:
      - ./sentinel3.conf:/usr/local/etc/redis/sentinel.conf

# 定义网络：local_redis_sentinel_net
networks:
  local_redis_sentinel_net:
    name: local_redis_sentinel_net
```

创建`sentinel1.conf`，内容如下：

```yaml
port 26379
  # requirepass 12345678
protected-mode no
dir "/tmp"
sentinel resolve-hostnames yes
  # master的地址：redis-master， master端口:6379，2为最小投票数
sentinel monitor mymaster redis-master 6379 2
sentinel config-epoch mymaster 0
  # sentinel auth-pass mymaster 12345678
sentinel leader-epoch mymaster 0
sentinel deny-scripts-reconfig yes
```

创建`sentinel2.conf`，内容如下：

```yaml
port 26379
# requirepass 12345678
protected-mode no
dir "/tmp"
sentinel resolve-hostnames yes
# master的地址：redis-master， master端口:6379，2为最小投票数
sentinel monitor mymaster redis-master 6379 2
sentinel config-epoch mymaster 0
# sentinel auth-pass mymaster 12345678
sentinel leader-epoch mymaster 0
sentinel deny-scripts-reconfig yes
```

创建`sentinel3.conf`，内容如下：

```yaml
port 26379
# requirepass 12345678
protected-mode no
dir "/tmp"
sentinel resolve-hostnames yes
# master的地址：redis-master， master端口:6379，2为最小投票数
sentinel monitor mymaster redis-master 6379 2
sentinel config-epoch mymaster 0
# sentinel auth-pass mymaster 12345678
sentinel leader-epoch mymaster 0
sentinel deny-scripts-reconfig yes
```

启动：

```shell
docker compose up -d
```

等待启动后可以登录到redis集群上面查看状态。

# 使用redis源码包编译进行搭建

下载redis源码包并解压到需要的目录下，使用的示例目录为`~/develop/redis-sentinel/`，使用的版本是`redis-6.2.5`。进入到解压后的目录下`redis-6.2.5`，运行命令`make`进行编译，编译好之后在`redis-6.2.5/src`目录下会出现`redis-cli`、`redis-server`、`redis-sentinel`等可执行文件。

接下来将编译好的`redis-6.2.5`目录在示例目录`~/develop/redis-sentinel/`下面复制出来6份，分别命名为：

- `redis-6.2.5-master`
- `redis-6.2.5-slave1`
- `redis-6.2.5-slave2`
- `redis-6.2.5-sentinel1`
- `redis-6.2.5-sentinel2`
- `redis-6.2.5-sentinel3`

在示例目录下面创建配置文件如下：

- `master.conf`
- `slave1.conf`
- `slave2.conf`
- `sentinel1.conf`
- `sentinel2.conf`
- `sentinel3.conf`

此时示例目录`~/develop/redis-sentinel/`中的结构如下：

```text
.
├── master.conf
├── redis-6.2.5
├── redis-6.2.5-master
├── redis-6.2.5-sentinel1
├── redis-6.2.5-sentinel2
├── redis-6.2.5-sentinel3
├── redis-6.2.5-slave1
├── redis-6.2.5-slave2
├── sentinel1.conf
├── sentinel2.conf
├── sentinel3.conf
├── slave1.conf
└── slave2.conf
```

`master.conf`中的内容如下：

```text
port 6379
```

`slave1.conf`中的内容如下：

```text
port 6380
slaveof 127.0.0.1 6379
```

`slave2.conf`中的内容如下：

```text
port 6381
slaveof 127.0.0.1 6379
```

`sentinel1.conf`中的内容如下：

```text
port 26379
bind 127.0.0.1
# requirepass 12345678
protected-mode no
sentinel resolve-hostnames yes
# master的地址：127.0.0.1， master端口:6379，2为最小投票数
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel config-epoch mymaster 0
# sentinel auth-pass mymaster 12345678
sentinel leader-epoch mymaster 0
sentinel deny-scripts-reconfig yes
```

`sentinel2.conf`中的内容如下：

```text
port 26380
bind 127.0.0.1
# requirepass 12345678
protected-mode no
sentinel resolve-hostnames yes
# master的地址：127.0.0.1， master端口:6379，2为最小投票数
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel config-epoch mymaster 0
# sentinel auth-pass mymaster 12345678
sentinel leader-epoch mymaster 0
sentinel deny-scripts-reconfig yes
```

`sentinel3.conf`中的内容如下：

```text
port 26381
bind 127.0.0.1
# requirepass 12345678
protected-mode no
sentinel resolve-hostnames yes
# master的地址：127.0.0.1， master端口:6379，2为最小投票数
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel config-epoch mymaster 0
# sentinel auth-pass mymaster 12345678
sentinel leader-epoch mymaster 0
sentinel deny-scripts-reconfig yes
```

进入master1目录下并启动：

```shell
cd redis-6.2.5-master/src
./redis-server ../../master.conf
```

进入slave1目录下并启动：

```shell
cd redis-6.2.5-slave1/src
./redis-server ../../slave1.conf
```

进入slave2目录下并启动：

```shell
cd redis-6.2.5-slave2/src
./redis-server ../../slave2.conf
```

进入sentinel1目录下并启动：

```shell
cd redis-6.2.5-sentinel1/src
./redis-server ../../sentinel1.conf
```

进入sentinel2目录下并启动：

```shell
cd redis-6.2.5-sentinel2/src
./redis-server ../../sentinel2.conf
```

进入sentinel3目录下并启动：

```shell
cd redis-6.2.5-sentinel3/src
./redis-server ../../sentinel3.conf
```

这样启动后可以在控制台上看到日志。