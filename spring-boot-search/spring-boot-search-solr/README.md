# Solr

- version: `9.0`

# 安装、配置、启动Solr

本地测试使用docker安装solr，命令如下：

```
docker network create solr
docker pull solr:9.0.0
docker run -d --name solr --net solr -p 8983:8983 solr:9.0.0
```

访问Solr Admin Console：

- 浏览器访问：`http://localhost:8983`

