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

## _cat查看集群运行状况

- `GET /_cat/health?v` 查看健康状态
- `GET /_cat/nodes?v` 查看集群中的节点
- `GET /_cat/indices?v` 查看集群中索引信息

## 创建索引

使用`PUT /user?pretty`创建一个名为answer的索引，pretty可以格式化打印响应的Json，创建索引成功后会返回如下的信息：

```json
{
"acknowledged": true,
"shards_acknowledged": true,
"index": "user"
}
```

## 删除索引

使用`DELETE /user?pretty`进行索引删除，返回的结果如下：

```json
{"acknowledged":true}
```

## 创建文档

使用：

```
PUT /user/_doc/1?pretty
{
  "name": "xiao ming"
}
```

执行完后返回的信息如下：

```json
{
  "_index" : "user",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

```

## 查询文档

使用`GET /user/_doc/1?pretty`查询刚创建的文档，返回的结果如下：

```json
{
"_index": "user",
"_id": "1",
"_version": 1,
"_seq_no": 0,
"_primary_term": 1,
"found": true,
"_source": {
"name": "xiao ming"
}
}
```

## 批量查询文档_mget

URL中不指定索引，Body中指定多个docs：

```
GET /_mget
{
  "docs": [
    {
      "_index": "user",
      "_id": "1"
    },
    {
      "_index": "user",
      "_id": "2"
    }
  ]
}
```

URL中指定索引，Body中指定多个docs：

```
GET /user/_mget
{
  "docs": [
    {
      "_id": "1"
    },
    {
      "_id": "2"
    }
  ]
}
```

URL中指定索引，Body中使用ids指定多个id：

```
GET /user/_mget
{
  "ids": ["1","2"]
}
```


## 覆盖文档

使用：

```
PUT /user/_doc/1?pretty
{
  "name": "wang xiao ming"
}
```

返回的结果如下：

```json
{
"_index": "user",
"_id": "1",
"_version": 2,
"result": "updated",
"_shards": {
"total": 2,
"successful": 1,
"failed": 0
},
"_seq_no": 1,
"_primary_term": 1
}
```

## 更新文档_update

### 更新文档已有字段

更新文档ES是先删除再新增，使用如下：

```
POST /user/_update/1?pretty
{
  "doc": {
    "name": "huang xiao ming"
  }
}
```

返回的结果如下：

```json
{
  "_index": "user",
  "_id": "1",
  "_version": 3,
  "result": "updated",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 2,
  "_primary_term": 1
}
```

### 更新文档已有字段并新增字段

使用：

```
POST /user/_update/1?pretty
{
  "doc": {
    "name": "huang xiao ming",
    "age": 30
  }
}
```

返回的结果如下：

```json
{
  "_index": "user",
  "_id": "1",
  "_version": 4,
  "result": "updated",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 3,
  "_primary_term": 1
}
```

### 使用脚本更新

使用：

```
POST /user/_update/1?pretty
{
  "script": "ctx._source.age += 5"
}
```

返回的结果如下：

```json
{
  "_index": "user",
  "_id": "1",
  "_version": 5,
  "result": "updated",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 4,
  "_primary_term": 1
}
```

### 查询更新_update_by_query

```
POST /user/_update_by_query/
{
  "query":{
    "match":{
      "doc.age": 100
    }
  }
}
```


## 删除文档

### 删除：DELETE

```
DELETE /user/_doc/2?pretty
```

返回的结果：

```json
{
  "_index" : "user",
  "_id" : "2",
  "_version" : 2,
  "result" : "deleted",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 8,
  "_primary_term" : 1
}
```

### 查询删除：_delete_by_query

将与查询匹配的文档进行删除：

```
POST /user/_delete_by_query
{
  "query":{
    "match":{
      "doc.age": 100
    }
  }
}
```

## 批量操作

如下是先更新ID为1的文档，在删除ID为2的文档：

```
POST /user/_bulk?pretty
{"update": {"_id": "1"}}
{"doc":{"name":"liu xiao ming"}}
{"delete":{"_id":"2"}}
```

返回的结果：

```json
{
  "took" : 9,
  "errors" : false,
  "items" : [
    {
      "update" : {
        "_index" : "user",
        "_id" : "1",
        "_version" : 11,
        "result" : "updated",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 14,
        "_primary_term" : 1,
        "status" : 200
      }
    },
    {
      "delete" : {
        "_index" : "user",
        "_id" : "2",
        "_version" : 3,
        "result" : "deleted",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 15,
        "_primary_term" : 1,
        "status" : 200
      }
    }
  ]
}
```

## 搜索API：_search

### 使用URI传递参数的方式搜索

`GET /user/_search?q=*&sort=doc.age:asc&pretty`，URL中参数如下：

- `user`为要搜索的索引
- `_search`表示是搜索
- `q=*`表示匹配所有文档
- `sort=doc.age:asc`表示使用age字段对结果进行升序排序
- `pretty`打印格式化的Json

得到的结果如下：

```json
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [
      {
        "_index" : "user",
        "_id" : "3",
        "_score" : null,
        "_source" : {
          "doc" : {
            "name" : "zhao zi long",
            "age" : 20
          }
        },
        "sort" : [
          20
        ]
      },
      {
        "_index" : "user",
        "_id" : "2",
        "_score" : null,
        "_source" : {
          "doc" : {
            "name" : "han dong",
            "age" : 30
          }
        },
        "sort" : [
          30
        ]
      },
      {
        "_index" : "user",
        "_id" : "1",
        "_score" : null,
        "_source" : {
          "doc" : {
            "name" : "huang xiao ming",
            "age" : 35
          }
        },
        "sort" : [
          35
        ]
      }
    ]
  }
}
```

结果中的字段如下：

- took表示搜索用的时间，单位ms
- timed_out表示是否超时
- _shards表示搜索了多少分片、成功了多少、失败了多少、跳过了多少分片
- hits表示搜索命中的结果集

### 使用请求体BODY中传递参数进行搜索

```
GET /user/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "doc.age": "asc"
    }
  ]
}
```

### 返回特定字段

默认情况下搜索会返回完整的Json文档，即_source字段。如果不希望返回全部字段，可以指定要返回的字段，如下只返回name字段：

```
GET /user/_search
{
  "query": {
    "match_all": {}
  },
  "_source": [
    "name"
  ]
}
```

### 匹配查询

根据字段进行匹配，返回年龄等于20的：

```
GET /user/_search
{
  "query": {
    "match": {"doc.age": 20}
  }
}
```

返回名字中包含xiao ming的：

```
GET /user/_search
{
  "query": {
    "match": {"doc.name": "xiao ming"}
  }
}
```

### 布尔查询：bool

布尔查询是指使用布尔逻辑的方式把基本的查询组合成复杂查询。

#### must

如下返回name中包含han和dong的文档：

```
GET /user/_search
{
  "query": {
    "bool": {
      "must": [
        {"match": {"doc.name": "han"}},
        {"match": {"doc.name": "dong"}}
      ]
    }
  }
}
```

#### should

如下返回name中包含han或huang的文档：

```
GET /user/_search
{
  "query": {
    "bool": {
      "should": [
        {"match": {"doc.name": "han"}},
        {"match": {"doc.name": "huang"}}
      ]
    }
  }
}
```

#### must_not

如下返回name中既不包含han也不包含huang的文档：

```
GET /user/_search
{
  "query": {
    "bool": {
      "should": [
        {"match": {"doc.name": "han"}},
        {"match": {"doc.name": "huang"}}
      ]
    }
  }
}
```

### 聚合查询：aggregation

如下使用age对文档进行聚合查询：

```
GET /user/_search
{
  "size": 0, 
  "aggs": {
    "group_by_age": {
      "terms": {
        "field": "doc.age"
      }
    }
  }
}
```

返回的结果如下：

```json
{
  "took" : 6,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "group_by_age" : {
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 0,
      "buckets" : [
        {
          "key" : 20,
          "doc_count" : 1
        },
        {
          "key" : 30,
          "doc_count" : 1
        },
        {
          "key" : 35,
          "doc_count" : 1
        }
      ]
    }
  }
}
```

## 聚合

- avg 平均值
- weighted_avg 带权重的平均值
- cardinality 计数，基于HyperlogLog++算法
- extended_stats 统计聚合
- max 最大值
- min 最小值
- sum 求和
- percentiles 百分位数聚合
- percentile_ranks 百分比排名聚合