# 安装、配置、启动ES

本地测试使用docker安装es，命令如下：

```
docker network create elastic
docker pull docker.elastic.co/elasticsearch/elasticsearch:8.2.2
docker run --name es01 --net elastic -p 9200:9200 -p 9300:9300 -it docker.elastic.co/elasticsearch/elasticsearch:8.2.2

```

启动es之后可以在打印的日志中找到用户名、密码、以及在Kibana上使用的enrollment token，找到了之后记下来，后续登录ES以及Kibana使用，如果没记下来可以使用下面的进行重置密码以及重置enrollment token。

重置密码：

```
docker exec -it {elastic_search_container_id} /bin/bash
bin/elasticsearch-reset-password --username elastic -i
```

账号密码：

- elastic/12345678

重置Kibana使用的enrollment token：

```
docker exec -it {elastic_search_container_id} /bin/bash
bin/elasticsearch-create-enrollment-token --scope kibana
```

访问elasticsearch:

- 浏览器直接访问：https://localhost:9200，弹框中输入账号密码
- Chrome上使用ElasticSearch Head插件，地址输入：https://localhost:9200/?auth_user=elastic&auth_password=12345678
- 使用Kibana访问，Kibana --> 左侧主菜单 --> Management --> Dev Tools

# 安装、配置、启动Kibana

本地测试使用docker安装Kibana，命令如下：

```
docker pull docker.elastic.co/kibana/kibana:8.2.2
docker run --name kibana --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.2.2
```

可以使用如下命令重新获取Verification code：

```
docker exec -it {kibana_container_id} /bin/bash
bin/kibana-verification-code
```

访问Kibana：

- 浏览器：http://localhost:5601/

# 使用

