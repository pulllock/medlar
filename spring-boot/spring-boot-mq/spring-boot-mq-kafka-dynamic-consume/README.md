# 安装kafka

## 使用docker安装kafka

### 创建网络

1. `docker network create local_net`

### 安装zookeeper

1. 拉镜像：`docker pull zookeeper:3.8.1`
2. 启动容器：`docker run -d --network local_net -p 2181:2181 --name zookeeper zookeeper:3.8.1`
3. 使用命令行客户端连接到zookeeper：`docker run -it --rm --network local_net zookeeper zkCli.sh -server zookeeper`
4. 使用其他工具连接到zookeeper，地址：`127.0.0.1：2181`或者`localhost:2181`或者`zookeeper:2181`

### 安装kafka

1. 拉镜像：`docker pull bitnami/kafka:3.3.2`
2. 启动容器：`docker run -d --network local_net -p 9092:9092 --name kafka -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 -e ALLOW_PLAINTEXT_LISTENER=yes -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092 -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092 -e KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE=true bitnami/kafka:3.3.2`
3. 启动kafka客户端连接到kafka：`docker run -it --rm --network local_net -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 bitnami/kafka:3.3.2 kafka-topics.sh --list --bootstrap-server kafka:9092`

## 使用docker compose安装kafka

1. 创建`docker-compose.yml`文件，内容参照下方`docker-compose.yml`文件内容
2. 启动`docker compose up -d`
3. 连接到zookeeper进行验证：`docker run -it --rm --network local_net zookeeper zkCli.sh -server zookeeper`
4. 连接到kafka进行验证：`docker run -it --rm --network local_net -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 bitnami/kafka:3.3.2 kafka-topics.sh --list --bootstrap-server kafka:9092`

### docker-compose.yml文件内容

```yaml
version: "3.8"

# 定义网络：local_net
networks:
  local_net:
    name: local_net

# 定义服务
services:
  # zookeeper服务
  zookeeper:
    image: zookeeper:3.8.1
    ports:
      - 2181:2181
    networks:
      - local_net

  # kafka服务
  kafka:
    image: bitnami/kafka:3.3.2
    ports:
      - 9092:9092
    networks:
      - local_net
    depends_on:
      - zookeeper
    environment:
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
      - KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE=true
```

## 手动安装kafka

### 安装zookeeper

该安装可选，kafka中自带了zookeeper，不想单独安装的直接跳过该步骤。

1. 下载zookeeper
2. 解压zookeeper，并放到想要的目录
3. 到zookeeper的conf目录下复制一份`zoo_sample.cfg`文件，并重命名为：`zoo.cfg`
4. 修改`zoo.cfg`配置文件中的`dataDir=/你想要的目录/zookeeper/data`
5. 到zookeeper的bin目录下启动zookeeper：`sh ./zkServer.sh start`
6. 使用命令行客户端连接到zookeeper，到zookeeper的bin目录下执行`sh ./zkCli.sh -server localhost`
7. 使用其他工具连接到zookeeper，地址：`127.0.0.1：2181`或者`localhost:2181`

### 安装kafka

1. 下载kafka
2. 解压缩kafka，并放到想要的目录
3. 到kafka的bin目录下启动zookeeper：`sh ./zookeeper-server-start.sh ../config/zookeeper.properties`
4. 使用其他工具连接到zookeeper进行验证，地址：`127.0.0.1：21:81`或者`localhost:2181`
5. 到kafka的bin目录下启动kafka：`sh ./kafka-server-start.sh ../config/server.properties`

# 基础概念

- Producer：生产者
- Consumer：消费者
- Broker：服务代理节点
- Topic：主题，消息以Topic为单位进行归类
- Partition：分区，一个Topic可分为多个Partition，一个Partition只属于单个Topic
- AR（Assigned Replicas）：分区中所有副本统称AR
- ISR（In-Sync Replicas）：所有与Leader副本保持一定程度同步的副本组成ISR
- OSR（Out-of-Sync Replicas）：与leader副本同步滞后过多的副本组成OSR
- LEO（Log End Offset）：标识当前日志文件中下一条待写入消息的offset
- HW（High Watermark）：高水位，标识一个特定的消息偏移量，消费者只能拉取到这个偏移量之前的消息。ISR集合中最小的LEO即为分区的HW。