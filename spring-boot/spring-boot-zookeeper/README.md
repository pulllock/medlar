# spring-boot-zookeeper

使用docker运行zookeeper：

```shell
docker pull zookeeper:3.7.1
docker run -d -p 2181:2181 --name zookeeper zookeeper:3.7.1
```