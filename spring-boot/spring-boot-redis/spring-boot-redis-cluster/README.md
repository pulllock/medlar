# redis cluster

搭建Redis集群，3主、3从：

- master: 3
- slave: 3

## 使用redis源码包编译进行搭建

下载redis源码包并解压到需要的目录下，使用的示例目录为`~/develop/redis-cluster/`，使用的版本是`redis-6.2.5`。进入到解压后的目录下`redis-6.2.5`，运行命令`make`进行编译，编译好之后在`redis-6.2.5/src`目录下会出现`redis-cli`、`redis-server`、`redis-sentinel`等可执行文件。

接下来将编译好的`redis-6.2.5`目录在示例目录`~/develop/redis-cluster/`下面复制出来6份，分别命名为：

- `redis-6.2.5-cluster1`
- `redis-6.2.5-cluster2`
- `redis-6.2.5-cluster3`
- `redis-6.2.5-cluster4`
- `redis-6.2.5-cluster5`
- `redis-6.2.5-cluster6`

在示例目录下面创建配置文件如下：

- `cluster1.conf`
- `cluster2.conf`
- `cluster3.conf`
- `cluster4.conf`
- `cluster5.conf`
- `cluster6.conf`

此时示例目录`~/develop/redis-cluster/`中的结构如下：

```text
.
├── cluster1.conf
├── cluster2.conf
├── cluster3.conf
├── cluster4.conf
├── cluster5.conf
├── cluster6.conf
├── redis-6.2.5
├── redis-6.2.5-cluster1
├── redis-6.2.5-cluster2
├── redis-6.2.5-cluster3
├── redis-6.2.5-cluster4
├── redis-6.2.5-cluster5
├── redis-6.2.5-cluster6
```

`cluster1.conf`中的内容如下：

```text
port 6379
cluster-enabled yes
```

`cluster2.conf`中的内容如下：

```text
port 6380
cluster-enabled yes
```

`cluster3.conf`中的内容如下：

```text
port 6381
cluster-enabled yes
```

`cluster4.conf`中的内容如下：

```text
port 6382
cluster-enabled yes
```

`cluster5.conf`中的内容如下：

```text
port 6383
cluster-enabled yes
```

`cluster6.conf`中的内容如下：

```text
port 6384
cluster-enabled yes
```

进入cluster1目录下并启动：

```shell
cd redis-6.2.5-cluster1/src
./redis-server ../../cluster1.conf
```

进入cluster2目录下并启动：

```shell
cd redis-6.2.5-cluster2/src
./redis-server ../../cluster2.conf
```

进入cluster3目录下并启动：

```shell
cd redis-6.2.5-cluster3/src
./redis-server ../../cluster3.conf
```

进入cluster4目录下并启动：

```shell
cd redis-6.2.5-cluster4/src
./redis-server ../../cluster4.conf
```

进入cluster5目录下并启动：

```shell
cd redis-6.2.5-cluster5/src
./redis-server ../../cluster5.conf
```

进入cluster6目录下并启动：

```shell
cd redis-6.2.5-cluster6/src
./redis-server ../../cluster6.conf
```

这样启动后可以在控制台上看到日志。

进入示例目录`~/develop/redis-cluster/`下的`redis-6.2.5`的`src`目录下指定如下命令：

```shell
cd redis-6.2.5/src
./redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1
```

这样即可启动集群。

# redis cluster加sentinel

不知道这样是不是可以？是不是有用？

搭建Redis集群，3主、3从、3哨兵：

- master: 3
- slave: 3
- sentinel：3

## 使用redis源码包编译进行搭建

下载redis源码包并解压到需要的目录下，使用的示例目录为`~/develop/redis-cluster/`，使用的版本是`redis-6.2.5`。进入到解压后的目录下`redis-6.2.5`，运行命令`make`进行编译，编译好之后在`redis-6.2.5/src`目录下会出现`redis-cli`、`redis-server`、`redis-sentinel`等可执行文件。

接下来将编译好的`redis-6.2.5`目录在示例目录`~/develop/redis-cluster/`下面复制出来9份，分别命名为：

- `redis-6.2.5-cluster1`
- `redis-6.2.5-cluster2`
- `redis-6.2.5-cluster3`
- `redis-6.2.5-cluster4`
- `redis-6.2.5-cluster5`
- `redis-6.2.5-cluster6`
- `redis-6.2.5-sentinel1`
- `redis-6.2.5-sentinel2`
- `redis-6.2.5-sentinel3`

在示例目录下面创建配置文件如下：

- `cluster1.conf`
- `cluster2.conf`
- `cluster3.conf`
- `cluster4.conf`
- `cluster5.conf`
- `cluster6.conf`
- `sentinel1.conf`
- `sentinel2.conf`
- `sentinel3.conf`

此时示例目录`~/develop/redis-cluster/`中的结构如下：

```text
.
├── cluster1.conf
├── cluster2.conf
├── cluster3.conf
├── cluster4.conf
├── cluster5.conf
├── cluster6.conf
├── redis-6.2.5
├── redis-6.2.5-cluster1
├── redis-6.2.5-cluster2
├── redis-6.2.5-cluster3
├── redis-6.2.5-cluster4
├── redis-6.2.5-cluster5
├── redis-6.2.5-cluster6
├── redis-6.2.5-sentinel1
├── redis-6.2.5-sentinel2
├── redis-6.2.5-sentinel3
├── sentinel1.conf
├── sentinel2.conf
└── sentinel3.conf
```

`cluster1.conf`中的内容如下：

```text
port 6379
cluster-enabled yes
```

`cluster2.conf`中的内容如下：

```text
port 6380
cluster-enabled yes
```

`cluster3.conf`中的内容如下：

```text
port 6381
cluster-enabled yes
```

`cluster4.conf`中的内容如下：

```text
port 6382
cluster-enabled yes
```

`cluster5.conf`中的内容如下：

```text
port 6383
cluster-enabled yes
```

`cluster6.conf`中的内容如下：

```text
port 6384
cluster-enabled yes
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
sentinel monitor mymaster 127.0.0.1 6380 2
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
sentinel monitor mymaster 127.0.0.1 6381 2
sentinel config-epoch mymaster 0
# sentinel auth-pass mymaster 12345678
sentinel leader-epoch mymaster 0
sentinel deny-scripts-reconfig yes
```

进入cluster1目录下并启动：

```shell
cd redis-6.2.5-cluster1/src
./redis-server ../../cluster1.conf
```

进入cluster2目录下并启动：

```shell
cd redis-6.2.5-cluster2/src
./redis-server ../../cluster2.conf
```

进入cluster3目录下并启动：

```shell
cd redis-6.2.5-cluster3/src
./redis-server ../../cluster3.conf
```

进入cluster4目录下并启动：

```shell
cd redis-6.2.5-cluster4/src
./redis-server ../../cluster4.conf
```

进入cluster5目录下并启动：

```shell
cd redis-6.2.5-cluster5/src
./redis-server ../../cluster5.conf
```

进入cluster6目录下并启动：

```shell
cd redis-6.2.5-cluster6/src
./redis-server ../../cluster6.conf
```

这样启动后可以在控制台上看到日志。

进入示例目录`~/develop/redis-cluster/`下的`redis-6.2.5`的`src`目录下指定如下命令：

```shell
cd redis-6.2.5/src
./redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1
```

这样即可启动集群。

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