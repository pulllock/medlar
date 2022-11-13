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

由于访问es默认需要使用https，需要从docker中复制http_ca.crt文件到本地，命令如下：

```
docker cp es01:/usr/share/elasticsearch/config/certs/http_ca.crt /目的地址
```

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

# 示例数据

## Kibana提供的示例数据

### 新的数据集

新版本Kibana有提供示例数据，在Kibana首页的Try sample data中就可以直接导入示例数据。

### 旧的数据集

可以在这个页面下载：https://www.elastic.co/guide/cn/kibana/current/tutorial-load-dataset.html，有三个文件：shakespeare_6.0.json、accounts.zip、logs.jsonl.gz，后面两个需要解压一下使用。

#### 导入shakespeare_6.0.json示例数据

首先创建mapping：

```
PUT /shakespeare
{
  "mappings": {
    "properties": {
      "speaker": {
        "type": "keyword"
      },
      "play_name": {
        "type": "keyword"
      },
      "line_id": {
        "type": "integer"
      },
      "speech_number": {
        "type": "integer"
      }
    }
  }
}
```

导入数据：

由于elasticsearch8启用了https连接，需要使用证书来访问，也就是上面说的http_ca.crt，所以使用的命令如下：

```
curl --cacert ./http_ca.crt -u elastic:12345678 -H 'Content-Type: application/x-ndjson' -XPOST 'https://localhost:9200/_bulk?pretty' --data-binary @shakespeare_6.0.json
```

不启用https的使用如下命令：

```
curl -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/_bulk?pretty' --data-binary @shakespeare_6.0.json
```

#### 导入logs.jsonl.gz示例数据

首先创建mapping：

```
PUT /logstash-2015.05.18
{
  "mappings": {
    "properties": {
      "geo": {
        "properties": {
          "coordinates": {
            "type": "geo_point"
          }
        }
      }
    }
  }
}

PUT /logstash-2015.05.19
{
  "mappings": {
    "properties": {
      "geo": {
        "properties": {
          "coordinates": {
            "type": "geo_point"
          }
        }
      }
    }
  }
}

PUT /logstash-2015.05.20
{
  "mappings": {
    "properties": {
      "geo": {
        "properties": {
          "coordinates": {
            "type": "geo_point"
          }
        }
      }
    }
  }
}
```

导入数据：

由于elasticsearch8启用了https连接，需要使用证书来访问，也就是上面说的http_ca.crt，所以使用的命令如下，另外还需要将数据文件中的`"_type":"log"`这部分全部去除才行：

```
curl --cacert ./http_ca.crt -u elastic:12345678 -H 'Content-Type: application/x-ndjson' -XPOST 'https://localhost:9200/_bulk?pretty' --data-binary @logs.jsonl
```

不启用https的使用如下命令：

```
curl -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/_bulk?pretty' --data-binary @logs.jsonl
```

#### 导入accounts.json示例数据

不需要创建mapping， 直接导入数据：

由于elasticsearch8启用了https连接，需要使用证书来访问，也就是上面说的http_ca.crt，所以使用的命令如下：

```
curl --cacert ./http_ca.crt -u elastic:12345678 -H 'Content-Type: application/x-ndjson' -XPOST 'https://localhost:9200/accounts/_bulk?pretty' --data-binary @accounts.json
```

不启用https的使用如下命令：

```
curl -H 'Content-Type: application/x-ndjson' -XPOST 'localhost:9200/accounts/_bulk?pretty' --data-binary @accounts.json
```

## ElasticSearch提供的示例数据

可以ElasticSearch提供的数据集：https://github.com/elastic/examples下的Exploring Public Datasets目录下，需要按照各个目录下文档来进行数据的导入。

# 索引模块

## 索引设置（Index Settings）

索引级别的设置分为两种：

- 静态的设置：只能在索引创建的时候设置或者索引是closed的时候设置
- 动态的设置：可通过更新接口动态设置

### 静态的设置

- `index.number_of_shards`：索引的主分片数，只能在索引创建的时候设置，默认1，最大1024
- `index.number_of_routing_shards`：
- `index.codec`：
- `index.routing_partition_size`：只能在索引创建的时候设置，默认1
- `index.soft_deletes.enabled`：只能在索引创建的时候设置，默认true
- `index.soft_deletes.retention_lease.period`：默认12小时
- `index.load_fixed_bitset_filters_eagerly`：默认true
- `index.shard.check_on_startup`：默认false
  - false
  - checksum
  - true


### 动态的设置

- `index.number_of_replicas`：主分片的副数量，默认1
- `index.auto_expand_replicas`：自动扩充的副本数量
- `index.search.idle.after`：默认30秒
- `index.refresh_interval`：刷新的时间，默认1秒
- `index.max_result_window`：from+size可以搜索的最大值，默认10000
- `index.max_inner_result_window`：from+size inner hit的最大值，默认100
- `index.max_rescore_window`：
- `index.max_docvalue_fields_search`：默认100
- `index.max_script_fields`：默认32
- `index.max_ngram_diff`：默认1
- `index.max_shingle_diff`：默认3
- `index.max_refresh_listeners`：
- `index.analyze.max_token_count`：默认10000
- `index.highlight.max_analyzed_offset`：1000000
- `index.max_terms_count`：默认65536
- `index.max_regex_length`：1000
- `index.query.default_field`：默认*
- `index.routing.allocation.enable`：默认all
- `index.routing.rebalance.enable`：默认all
- `index.gc_deletes`：默认60秒
- `index.default_pipeline`：
- `index.final_pipeline`：
- `index.hidden`：默认false

## Analysis模块

定义分析器（analyzers）、分词器（tokenizers）、过滤器（token filters、character filters），顺序如下：character filters（0个或多个）--> tokenizers（一个）--> token filters（0个或多个）。

- `analysis.analyzer.default`：
- `analysis.analyzer.default_search`：

示例1：

```
PUT /index_name
{
  "settings": {
    "analysis": {
      "analyzer": {
        "std_english": {
          "type": "standard",
          "stopwords": "_english_"
        }
      }
    }
  },
  "mapings": {
    "properties": {
      "text_field": {
        "type": "text",
        "analyzer": "standard",
        "fields": {
          "english": {
            "type": "text",
            "analyzer": "std_english"
          }
        }
      }
    }
  }
}
```

示例2：

```
PUT /index_name
{
  "settings": {
    "analysis": {
      "analyzer": {
        "my_custom_analyzer": {
          "type": "custom",
          "tokenizer": "standard",
          "char_filter": [
            "html_strip"
          ],
          "filter": [
            "lowercase",
            "asciifolding"
          ]
        }
      }
    }
  }
}
```

示例3：

```
PUT /index_name
{
  "settings": {
    "analysis": {
      "analyzer": {
        "my_custom_analyzer": {
          "char_filter": ["emoticons"],
          "tokenizer": "punctuation",
          "filter": [
            "lowercase",
            "endlish_stop"
          ]
        }
      },
      "tokenizer": {
        "punctuation": {
          "type": "pattern",
          "pattern": "[ .,!?]"
        }
      },
      "char_filter": {
        "emoticons": {
          "type": "mapping",
          "mappings": [
            ":) => _happy_",
            ":( => _sad_"
          ]
        }
      },
      "filter": {
        "endlish_stop": {
          "type": "stop",
          "stopwords": "_english_"
        }
      }
    }
  }
}
```

示例4：

```
PUT /index_name
{
  "settings": {
    "analysis": {
      "analyzer": {
        "default": {
          "type": "simple"
        }
      }
    }
  }
}
```



## 索引分片分配模块



- Shard allocation filtering
- Delayed allocation
- Total shads per node
- Data tier allocation

## Index blocks



## Mapper模块

### Mapping

- Mapping分为动态mapping和显式的mapping
- 7.0.0之前mapping定义需要包含一个type名字，7.0.0以及之后的版本不再需要type

### Dynamic mapping

#### Dynamic field mapping

默认的动态数据类型

| JSON数据类型                                         | `"dynamic":"true"`             | `"dynamic":"runtime"`          | 备注                                                         |
| ---------------------------------------------------- | ------------------------------ | ------------------------------ | ------------------------------------------------------------ |
| `null`                                               | 不添加字段                     | 不添加字段                     |                                                              |
| `true` or `false`                                    | `boolean`                      | `boolean`                      |                                                              |
| `double`                                             | `float`                        | `double`                       |                                                              |
| `long`                                               | `long`                         | `long`                         |                                                              |
| `object`                                             | `object`                       | 不添加字段                     |                                                              |
| `array`                                              | 依赖于数组中第一个不为null的值 | 依赖于数组中第一个不为null的值 |                                                              |
| `string`如果匹配到`dynamic_date_formats`中配置的格式 | `date`                         | `date`                         | `dynamic_date_formats`默认值：`["strict_date_optional_time","yyyy/MM/dd HH:mm:ss Z||yyyy/MM/dd Z"]` |
| `string`匹配到是数字                                 | `float` or `long`              | `double` or `long`             |                                                              |
| `string`如果不是日期也不是数组                       | `text`类型， `.keyword`子类型  | `keyword`                      |                                                              |

##### 禁用date检测

```json
PUT my-index-000001
{
  "mappings": {
    "date_detection": false
  }
}
```

##### 自定义date检测格式

```json
PUT my-index-000001
{
  "mappings": {
    "dynamic_date_formats": ["MM/dd/yyyy"]
  }
}
```

#### Dynamic templates

### 显式mapping

#### 使用显式mapping创建索引

```json
PUT /my-index-000001
{
  "mappings": {
    "properties": {
      "age":    { "type": "integer" },  
      "email":  { "type": "keyword"  }, 
      "name":   { "type": "text"  }     
    }
  }
}
```

#### 添加字段到已经存在的mapping

```json
PUT /my-index-000001/_mapping
{
  "properties": {
    "employee-id": {
      "type": "keyword",
      "index": false
    }
  }
}
```

#### 更新已存在的mapping的字段

不能更新已经存在的字段。

#### 查看索引的mapping

```json
GET /my-index-000001/_mapping
```

#### 查看mapping的指定的字段

```json
GET /my-index-000001/_mapping/field/employee-id
```

### 运行时字段

运行时字段允许在查询的时候才进行评估，使用运行时字段可以有如下的好处：

- 往已经存在的文档中添加字段，不需要重新索引数据
- 不需要理解索引是什么结构的
- 查询的时候可以直接覆盖掉返回的值
- 不需要修改已经有的schema，就可以定义一些特殊用途的字段

#### 定义运行时字段

```json
PUT my-index-000001/
{
  "mappings": {
    "runtime": {
      "day_of_week": {
        "type": "keyword",
        "script": {
          "source": "emit(doc['@timestamp'].value.dayOfWeekEnum.getDisplayName(TextStyle.FULL, Locale.ROOT))"
        }
      }
    },
    "properties": {
      "@timestamp": {"type": "date"}
    }
  }
}
```

运行时字段类型可以是如下的数据类型：

- `boolean`
- `composite`
- `date`
- `double`
- `geo_point`
- `ip`
- `keyword`
- `long`
- `lookup`

启用运行时字段需要设置dynamic为runtime：

```json
PUT my-index-000001
{
  "mappings": {
    "dynamic": "runtime",
    "properties": {
      "@timestamp": {
        "type": "date"
      }
    }
  }
}
```

#### 不使用脚本来定义运行时字段

```json
PUT my-index-000001/
{
  "mappings": {
    "runtime": {
      "day_of_week": {
        "type": "keyword"
      }
    }
  }
}
```

#### 更新和删除运行时字段

更新运行时字段直接使用同名字段覆盖即可，删除直接设置为null就行：

```json
PUT my-index-000001/_mapping
{
 "runtime": {
   "day_of_week": null
 }
}
```

#### 在搜索请求中定义运行时字段

```json
GET my-index-000001/_search
{
  "runtime_mappings": {
    "day_of_week": {
      "type": "keyword",
      "script": {
        "source": "emit(doc['@timestamp'].value.dayOfWeekEnum.getDisplayName(TextStyle.FULL, Locale.ROOT))"
      }
    }
  },
  "aggs": {
    "day_of_week": {
      "terms": {
        "field": "day_of_week"
      }
    }
  }
}
```

#### 使用其他的运行时字段来创建运行时字段

使用其他的字段创建运行时字段：

```json
PUT my-index-000001/_mapping
{
  "runtime": {
    "measures.start": {
      "type": "long"
    },
    "measures.end": {
      "type": "long"
    }
  }
}
```

使用运行时字段：

```json
GET my-index-000001/_search
{
  "aggs": {
    "avg_start": {
      "avg": {
        "field": "measures.start"
      }
    },
    "avg_end": {
      "avg": {
        "field": "measures.end"
      }
    }
  }
}
```

#### 查询的时候覆盖字段的值

如果运行时字段名字和已经存在的字段名字一样，则查询时运行时字段会覆盖已有的字段

```json
POST my-index-000001/_search
{
  "runtime_mappings": {
    "measures.voltage": {
      "type": "double",
      "script": {
        "source":
        """if (doc['model_number.keyword'].value.equals('HG537PU'))
        {emit(1.7 * params._source['measures']['voltage']);}
        else{emit(params._source['measures']['voltage']);}"""
      }
    }
  },
  "query": {
    "match": {
      "model_number": "HG537PU"
    }
  },
  "fields": ["measures.voltage"]
}
```

#### 获取运行时字段

使用`_search`接口的fields参数可以获取运行时字段的值，运行时字段不会在`_source`中展示。

#### 索引运行时字段

```json
PUT my-index-000001/
{
  "mappings": {
    "properties": {
      "timestamp": {
        "type": "date"
      },
      "temperature": {
        "type": "long"
      },
      "voltage": {
        "type": "double"
      },
      "node": {
        "type": "keyword"
      },
      "voltage_corrected": {
        "type": "double",
        "on_script_error": "fail", 
        "script": {
          "source": """
        emit(doc['voltage'].value * params['multiplier'])
        """,
          "params": {
            "multiplier": 4
          }
        }
      }
    }
  }
}
```

#### 使用运行时字段探索数据

### 字段数据类型

#### Common类型

- `binary` 二进制值，使用Base64编码成字符串
- `boolean` true或false
- `Keywords` 包含：`keyword`、`constant_keyword`、`wildcard`
- `Numbers` 数值类型，比如long、double
- `Dates` 日期类型，包括date和date_nanos
- `alias` 别名

#### 对象和关系类型

- object JSON对象
- flattened 一整个json对象当作一个字段的值
- nested 嵌套类型
- join 在同一个索引中定义文档的父子关系

#### 结构化的数据类型

- Range 范围类型，比如：`long_range`, `double_range`, `date_range`, `ip_range`
- ip ipv4地址和ipv6地址
- version 软件版本
- murmru3 计算和存储值的哈希

#### 聚合数据类型

- `aggregate_metric_double`
- histogram

#### 文本搜索类型

- text 包含text和`match_only_text`
- annotated-text
- completion
- `search_as_you_type`
- `token_count`

#### 文档排序类型

- dense_vector
- rank_feature
- rank_features

#### 空间数据类型

- geo_point
- geo_shape
- point
- shape

#### 其他类型

- percolator

#### 数组

没有专门的数组类型的字段类型，数字中所有的数据的类型必须一样

#### Multi-fields

可以为一个字段指定多个分析器。

## 合并模块

## Similarity模块

## 慢日志模块

## 存储模块

## Translog模块

## 保留历史模块

## 索引排序模块

## 索引压力模块

# 使用

## 创建文档

```
POST /customer/_doc/1
{
  "firstname": "Jennifer",
  "lastname": "Walters"
}
```

如果索引customer不存在，会自动创建一个索引，并创建文档。

## 使用文档ID查询文档

```
GET /customer/_doc/1
```

## 批量创建文档

```
PUT customer/_bulk
{ "create": { } }
{ "firstname": "Monica","lastname":"Rambeau"}
{ "create": { } }
{ "firstname": "Carol","lastname":"Danvers"}
{ "create": { } }
{ "firstname": "Wanda","lastname":"Maximoff"}
{ "create": { } }
{ "firstname": "Jennifer","lastname":"Takeda"}
```

每一行必须以换行符结束，最后一行也必须以换行符结束

## 搜索文档

```
GET customer/_search
{
  "query" : {
    "match" : { "firstname": "Jennifer" }
  }
}
```



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

## 映射

映射是对索引字段的定义，包括：数据类型、存储属性、分析器、词向量等。

```
PUT user 
{
  "mappings": {
    "properties": {
      "name": {"type": "text"},
      "age":{"type": "integer"},
      "create_time":{
        "type": "date",
        "format": "strict_date_optional_time_nanos||epoch_millis"
      }
    }
  }
}
```

### 数据类型

- 字符串类型：string
- 数字类型：long、integer、short、byte、double、float、hal_float、scaled_float
- 布尔类型：boolean
- 数组类型，es没有专门的数组类型，任何字段都可包含0到多个值，数组中所有值都必须具有相同的数据类型
- 日期类型
- keyword，不进行分词，直接作为一个Term索引，搜索时也只能用精确值搜索
- text，会进行分词
- geo，地理位置类型

### 映射的属性

- index：true、false，控制是否对字段值进行索引，未索引的字段不可用来搜索、排序、聚合等
- store：true、false，是否存储原始字段值
- 分析器属性，一般配置于text类型字段中，表示索引和搜索时使用的分析方法
- doc_values

# 使用示例

## 创建索引

索引名称：`forms_sub_health`，mappings定义：

```
PUT /forms_sub_health
{
  "mappings": {
    "properties": {
      "id": {
        "type": "long"
      },
      "user_id": {
        "type": "long"
      },
      "template_id": {
        "type": "long"
      },
      "form_id": {
        "type": "long"
      },
      "user_form_id": {
        "type": "long"
      },
      "gender": {
        "type": "keyword"
      },
      "age": {
        "type": "integer"
      },
      "height": {
        "type": "integer"
      },
      "weight": {
        "type": "integer"
      },
      "work": {
        "type": "keyword"
      },
      "activity": {
        "type": "text"
      },
      "physical_exam": {
        "type": "keyword"
      }
    }
  }
}
```

## 插入测试数据

测试数据文件：`forms_sub_health_answers_es.json`，在`test/resources/`目录下，使用如下命令导入es中：`curl -k --insecure --user elastic:12345678 -XPOST https://localhost:9200/forms_sub_health/_bulk -H 'Content-Type: application/json' --data-binary @forms_sub_health_answers_es.json`

## 按照性别分组统计

SQL:

```
POST /_sql?format=txt
{
  "query": "select gender, count(1) from forms_sub_health group by gender"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "group_by_gender": {
      "terms": {
        "field": "gender"
      }
    }
  }
}
```

## 按照性别分组统计并排序

SQL:

```
POST /_sql?format=txt
{
  "query": "select gender, count(*) as num from forms_sub_health group by gender order by num desc"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "group_by_gender": {
      "terms": {
        "field": "gender",
        "order": {
          "_count": "desc"
        }
      }
    }
  }
}
```

## 按照年龄分组统计后，对结果进行过滤

SQL:

```
POST /_sql?format=txt
{
  "query": "select gender, count(*) as num from forms_sub_health group by gender having num > 5000"
}
```

可以将SQL转换为DSL：

```
POST /_sql/translate
{
  "query": "select gender, count(*) as num from forms_sub_health group by gender having num > 5000"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "group_by_gender": {
      "terms": {
        "field": "gender"
      },
      "aggs": {
        "bucker_filter": {
          "bucket_selector": {
            "script": "params.count > 5000",
            "buckets_path": {
              "count": "_count"
            }
          }
        }
      }
    }
  }
}
```

## 求平均年龄

SQL:

```
POST /_sql?format=txt
{
  "query": "select avg(age) from forms_sub_health"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "avg_age": {
      "avg": {
        "field": "age"
      }
    }
  }
}
```

## 求不同性别的平均年龄

SQL:

```
POST /_sql?format=txt
{
  "query": "select gender, avg(age) as avg_age from forms_sub_health group by gender"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "group_by_gender": {
      "terms": {
        "field": "gender"
      },
      "aggs": {
        "avg_age": {
          "avg": {
            "field": "age"
          }
        }
      }
    }
  }
}
```

## 求部分用户的平均年龄

SQL:

```
POST /_sql?format=txt
{
  "query": "select avg(age) from forms_sub_health where user_id > 106666"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "query": {
    "range": {
      "user_id": {
        "gte": 106666
      }
    }
  },
  "aggs": {
    "avg_age": {
      "avg": {
        "field": "age"
      }
    }
  }
}
```

## 查询最大年龄

SQL:

```
POST /_sql?format=txt
{
  "query": "select max(age) from forms_sub_health"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "max_age": {
      "max": {
        "field": "age"
      }
    }
  }
}
```

## 查询最小年龄

SQL:

```
POST /_sql?format=txt
{
  "query": "select min(age) from forms_sub_health"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "min_age": {
      "min": {
        "field": "age"
      }
    }
  }
}
```

## 求年龄总和

SQL:

```
POST /_sql?format=txt
{
  "query": "select sum(age) from forms_sub_health"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "sum_age": {
      "sum": {
        "field": "age"
      }
    }
  }
}
```

## 统计数量

SQL:

```
POST /_sql?format=txt
{
  "query": "select count(id) from forms_sub_health"
}
```

DSL:

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "count_id": {
      "value_count": {
        "field": "id"
      }
    }
  }
}
```

## stats统计

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "stats_age": {
      "stats": {
        "field": "age"
      }
    }
  }
}
```

## 查询某个用户的数据

```
GET /forms_sub_health/_search
{
  "query": {
    "term": {
      "user_id": {
        "value": "100000"
      }
    }
  }
}
```

## 查询某个年龄段的数据

```
GET /forms_sub_health/_search
{
  "query": {
    "range": {
      "age": {
        "gte": 18,
        "lte": 60
      }
    }
  }
}
```

## 查询年龄大于某个值并且性别是男的数据

```
GET /forms_sub_health/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "range": {
            "age": {
              "gte": 18
            }
          }
        },
        {
          "term": {
            "gender": {
              "value": "male"
            }
          }
        }
      ]
    }
  }
}
```

## 查询年龄大于某个值并且性别是男性，但是身高不为0的数据

```
GET /forms_sub_health/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "range": {
            "age": {
              "gte": 18
            }
          }
        },
        {
          "term": {
            "gender": {
              "value": "male"
            }
          }
        }
      ],
      "must_not": [
        {
          "term": {
            "height": {
              "value": 0
            }
          }
        }
      ]
    }
  }
}
```

## 查询身高大于等于180或者年龄大于18岁的数据

```
GET /forms_sub_health/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "range": {
            "height": {
              "gte": 180
            }
          }
        },
        {
          "range": {
            "age": {
              "gte": 18
            }
          }
        }
      ]
    }
  }
}
```

## filter过滤查询

filter过滤查询不评分，效率高

```
GET /forms_sub_health/_search
{
  "query": {
    "bool": {
      "filter": {
        "term": {
          "gender": {
            "value": "male"
          }
        }
      }
    }
  }
}
```

## 求不同性别中年龄最大的数据-collapse

```
GET /forms_sub_health/_search
{
  "collapse": {
    "field": "gender",
    "inner_hits": {
      "name": "age",
      "size": 1,
      "sort": {
        "age": {
          "order": "asc"
        }
      }
    }
  }
}
```

## 按照从事工作进行分组

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "group_by_work": {
      "terms": {
        "field": "work"
      }
    }
  }
}
```

## 按照从事工作进行分组，并统计每个分组内男女的数量

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "group_by_work": {
      "terms": {
        "field": "work"
      },
      "aggs": {
        "group_by_gender": {
          "terms": {
            "field": "gender"
          }
        }
      }
    }
  }
}
```

## 统计工作是学生的人数

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "count_student": {
      "filter": {
        "term": {
          "work": {
            "value": "student"
          }
        }
      }
    }
  }
}
```

或者：

```
GET /forms_sub_health/_search
{
  "size": 0,
  "query": {
    "bool": {
      "filter": {
        "term": {
          "work": {
            "value": "student"
          }
        }
      }
    }
  }
}
```

## 统计工作是学生的平均年龄

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "avg_age_student": {
      "filter": {
        "term": {
          "work": {
            "value": "student"
          }
        }
      },
      "aggs": {
        "avg_age": {
          "avg": {
            "field": "age"
          }
        }
      }
    }
  }
}
```

## 分别统计职业是自由和学生的人数

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "count_free_student": {
      "filters": {
        "filters": {
          "work_free": {
            "term": {
              "work": {
                "value": "free"
              }
            }
          },
          "work_student": {
            "term": {
              "work": {
                "value": "student"
              }
            }
          }
        }
      }
    }
  }
}
```

## 分别统计职业是自由和学生的人数，以及年龄大于140的人数

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "count_free_student": {
      "filters": {
        "filters": {
          "work_free": {
            "term": {
              "work": {
                "value": "free"
              }
            }
          },
          "work_student": {
            "term": {
              "work": {
                "value": "student"
              }
            }
          },
          "age_gt_140": {
            "range": {
              "age": {
                "gt": 140
              }
            }
          }
        }
      }
    }
  }
}
```

## 将年龄按照0-18，19-100，100-max进行分组

range用from和to来定义区间，是左闭右开。

```
GET /forms_sub_health/_search
{
  "size": 0,
  "aggs": {
    "age_range": {
      "range": {
        "field": "age",
        "ranges": [
          {
            "to": "19"
          },
          {
            "from": "19",
            "to": "101"
          },
          {
            "from": "101"
          }
        ]
      }
    }
  }
}
```

## 按照从事工作进行分组，并获取每个组里面年龄最大的，同时展示每个组里面年龄排前2的人

```
GET /forms_sub_health/_search
{
  "collapse": {
    "field": "work",
    "inner_hits": {
      "name": "age_inner",
      "size": 2,
      "sort": {
        "age": {
          "order": "desc"
        }
      }
    }
  },
  "sort": {
    "age": {
      "order": "desc"
    }
  }
}
```

## 过滤所有男性，并且将过滤后的数据按照从事工作进行分组

先过滤再聚合，该结果中hits中是过滤后的数据：

```
GET /forms_sub_health/_search
{
  "size": 5,
  "query": {
    "bool": {
      "filter": {
        "term": {
          "gender": {
            "value": "male"
          }
        }
      }
    }
  },
  "aggs": {
    "group_by_work": {
      "terms": {
        "field": "work"
      }
    }
  }
}
```

先filter聚合再内嵌terms聚合，该结果中hits是过滤前的数据：

```
GET /forms_sub_health/_search
{
  "size": 5,
  "aggs": {
    "group_of_male": {
      "filter": {
        "term": {
          "gender": {
            "value": "male"
          }
        }
      },
      "aggs": {
        "count_of_work": {
          "terms":{
            "field": "work"
          }
        }
      }
    }
  }
}
```

## 过滤所有男性，并且将过滤后的数据按照从事工作进行分组，并求每个分组的平均年龄

```
GET /forms_sub_health/_search
{
  "size": 5,
  "aggs": {
    "group_of_male": {
      "filter": {
        "term": {
          "gender": {
            "value": "male"
          }
        }
      },
      "aggs": {
        "count_of_work": {
          "terms":{
            "field": "work"
          },
          "aggs": {
            "avg_age": {
              "avg": {
                "field": "age"
              }
            }
          }
        }
      }
    }
  }
}
```

## 过滤所有男性，并且将过滤后的数据按照从事工作进行分组，并求每个分组的平均年龄，并按照平均年龄排序

```
GET /forms_sub_health/_search
{
  "size": 5,
  "aggs": {
    "group_of_male": {
      "filter": {
        "term": {
          "gender": {
            "value": "male"
          }
        }
      },
      "aggs": {
        "count_of_work": {
          "terms":{
            "field": "work",
            "order": {
              "avg_age": "asc"
            }
          },
          "aggs": {
            "avg_age": {
              "avg": {
                "field": "age"
              }
            }
          }
        }
      }
    }
  }
}
```

## 过滤所有男性，并且将过滤后的数据按照从事工作进行分组，并求每个分组的平均年龄，并按照平均年龄排序，并过滤平均年龄大于75的分组的数据

```
GET /forms_sub_health/_search
{
  "size": 5,
  "aggs": {
    "group_of_male": {
      "filter": {
        "term": {
          "gender": {
            "value": "male"
          }
        }
      },
      "aggs": {
        "count_of_work": {
          "terms":{
            "field": "work",
            "order": {
              "avg_age": "asc"
            }
          },
          "aggs": {
            "avg_age": {
              "avg": {
                "field": "age"
              }
            },
            "having_age_gt_75": {
              "bucket_selector": {
                "buckets_path": {
                  "avg_age_param": "avg_age"
                },
                "script": "params.avg_age_param > 75"
              }
            }
          }
        }
      }
    }
  }
}
```

## 过滤所有男性，并且将过滤后的数据按照从事工作进行分组，并过滤每个分组中总数大于1250的数据

```
GET /forms_sub_health/_search
{
  "size": 5,
  "aggs": {
    "group_of_male": {
      "filter": {
        "term": {
          "gender": {
            "value": "male"
          }
        }
      },
      "aggs": {
        "count_of_work": {
          "terms":{
            "field": "work"
          },
          "aggs": {
            "having": {
              "bucket_selector": {
                "script": "params.count_of_group > 1250",
                "buckets_path": {
                  "count_of_group": "_count"
                }
              }
            }
          }
        }
      }
    }
  }
}
```

# ElasticSearch配置

## 配置文件位置

ElasticSearch有三个配置文件：

- `elasticsearch.yml` ElasticSearch的配置
- `jvm.options` ElasticSearch JVM设置
- `log4j2.properties` ElasticSearch日志配置

如果是通过压缩包进行安装的，则配置文件在`$ES_HOME/config`目录下，配置文件目录可以通过环境变量`ES_PATH_CONF`来设置。如果是通过操作系统的包管理软件进行安装的，则配置文件在`/etc/elasticsearch`目录下。

## Path设置

- `data`目录存放索引的数据
- `logs`目录存放ElasticSearch的日志

可以通过修改elasticsearch.yml配置修改data和logs的目录：

- `path.data`
- `path.logs`

示例：

```yaml
path:
  data: /var/data/elasticsearch
  logs: /var/log/elasticsearch
```

## 多个data目录

可以配置多个data目录：

```yaml
path:
  data:
    - /mnt/elasticsearch_1
    - /mnt/elasticsearch_2
    - /mnt/elasticsearch_3
```

多data目录配置在7.13.0被废弃了

## Cluster name设置

`cluster.name`默认是elasticsearch，可以在配置文件中设置自己的cluster名字：`cluster.name: logging-prod`

## Node name设置

设置node.name：`node.name: prod-data-2`

## 网络host设置

默认情况下ElasticSearch只绑定了回环地址：`127.0.0.1`和`[::1]`，可以通过`network.host`来配置实际的IP地址：`network.host: 192.169.1.10`

## 服务发现和集群设置

ElasticSearch默认绑定到本地地址的9300端口和9305端口，可以和同一服务器上的其他的节点进行通信，如果想要和其他的服务器上的节点进行通信，需要配置`discovery.seed_hosts`，示例：

```yaml
discovery.seed_hosts:
   - 192.168.1.10:9300
   - 192.168.1.11 
   - seeds.mydomain.com 
   - [0:0:0:0:0:ffff:c0a8:10c]:9301 
```

第一次启动集群的时候，需要设置参与选举master的节点配置：`cluster.initial_master_nodes`，配置的是节点名字。第一次选举成功后，需要将此设置移除掉。示例：

```yaml
cluster.initial_master_nodes: 
   - master-node-a
   - master-node-b
   - master-node-c
```

## 堆大小设置

Elasticsearch会根据节点的角色以及总的内存大小来自动设置JVM堆内存大小，还可以通过JVM参数来手动设置堆内存的大小。

## JVM堆dump路径设置

Elasticsearch默认设置dump路径在`/var/lib/elasticsearch`目录下或者在Elasticsearch安装目录下的data目录下，可以通过在`jvm.options`配置文件中设置JVM参数：`-XX:HeapDumpPath=...`来指定其他的路径

## GC日志设置

Elasticsearch默认记录GC日志，在`jvm.options`中设置，默认和Elasticsearch日志在同一个位置

## 临时目录设置

可以通过环境变量`$ES_TMPDIR`来设置临时目录位置

## JVM致命错误日志设置

Elasticsearch默认设置致命错误日志路径在`/var/lib/elasticsearch`目录下或者在Elasticsearch安装目录下的data目录下，可以通过在`jvm.options`配置文件中设置JVM参数：`-XX:ErrorFile=...`来指定其他的路径

## 集群备份

## 审计设置

- `xpack.security.audit.enabled` 静态配置，设置为true表示在节点上启用审计功能，会在每个节点上创建一个`<clustername>_audit.json`文件
- `xpack.security.audit.logfile.events.include` 动态配置，用来指定哪些事件需要被记录
- `xpack.security.audit.logfile.events.exclude` 动态配置，用来指定哪些事件不需要被记录
- `xpack.security.audit.logfile.events.emit_request_body` 动态配置，用来指定是否记录完整的请求体，默认false
- `xpack.security.audit.logfile.emit_node_name` 动态设置，是否将节点名字作为一个字段写到每个审计事件中，默认false
- `xpack.security.audit.logfile.emit_node_host_address` 动态设置，是否将节点的ip作为一个字段写到每个审计事件中，默认false
- `xpack.security.audit.logfile.emit_node_host_name` 动态设置，是否将节点的主机名作为一个字段写到每个审计事件中，默认false
- `xpack.security.audit.logfile.emit_node_id` 动态设置，，是否将节点的id为一个字段写到每个审计事件中，默认true
- `xpack.security.audit.logfile.events.ignore_filters.<policy_name>.users` 动态配置，可以配置用户名列表或者`*`，配置的这些用户名将不会打印该策略的审计事件
- `xpack.security.audit.logfile.events.ignore_filters.<policy_name>.realms` 动态配置，可以配置认证Realm的列表或者`*`，配置的这些将不会打印该策略的审计事件
- `xpack.security.audit.logfile.events.ignore_filters.<policy_name>.actions` 动态配置，可以配置操作的列表或者`*`，配置的这些操作将不会打印该策略的审计事件
- `xpack.security.audit.logfile.events.ignore_filters.<policy_name>.roles` 动态配置，可以配置角色的列表或者`*`，配置的这些角色将不会打印该策略的审计事件
- `xpack.security.audit.logfile.events.ignore_filters.<policy_name>.indices` 动态配置，可以配置索引名字的列表或者`*`，配置的这些索引将不会打印该策略的审计事件

## 熔断设置

Elasticsearch包含多个熔断器，用来保证某些操作不会引起OutOfMemoryError。

### 父熔断器

- `indices.breaker.total.use_real_memory` 静态配置，设置为true表示父熔断器应该考虑实际的内存使用，设置为false表示只考虑子熔断器保留的数量，默认为true
- `indices.breaker.total.limit` 动态配置，父熔断器的总的大小限制，如果`indices.breaker.total.use_real_memory`设置为`false`，则默认是Java堆的70%。如果`indices.breaker.total.use_real_memory`设置为`true`，则默认是JVM堆的95%

### 字段数据熔断器

字段数据熔断器会估算一个字段加载到字段数据缓存中需要多少的堆内存，如果需要的内存会导致缓存超过设置的内存限制，则熔断器会停止当前操作bingo返回错误。

- `indices.breaker.fielddata.limit` 动态配置，字段数据熔断器的大小限制，默认是JVM堆内存的40%
- `indices.breaker.fielddata.overhead` 动态配置，是一个常量值，估算字段数据需要的内存时会乘上这个值，默认是1.03

### 请求熔断器

防止每个请求需要的内存超过了总的内存设置。

- `indices.breaker.request.limit` 动态配置，Limit for request breaker, defaults to 60% of JVM heap.
- `indices.breaker.request.overhead` 动态配置，A constant that all request estimations are multiplied with to determine a final estimation. Defaults to `1`.

### In flight请求熔断器

- `network.breaker.inflight_requests.limit` 动态配置，Limit for in flight requests breaker, defaults to 100% of JVM heap. This means that it is bound by the limit configured for the parent circuit breaker.
- `network.breaker.inflight_requests.overhead` 动态配置，A constant that all in flight requests estimations are multiplied with to determine a final estimation. Defaults to 2.

### Accounting请求熔断器

- `indices.breaker.accounting.limit`动态配置
- `indices.breaker.accounting.overhead` 动态配置

### 脚本编译熔断器

- `script.max_compilations_rate` 动态配置，Limit for the number of unique dynamic scripts within a certain interval that are allowed to be compiled. Defaults to `150/5m`, meaning 150 every 5 minutes.

### 正则熔断器

- `script.painless.regex.enabled` 静态配置，Enables regex in Painless scripts. Accepts:
  - `limited` 默认值，Enables regex but limits complexity using the `script.painless.regex.limit-factor` cluster setting.
  - `true` Enables regex with no complexity limits. Disables the regex circuit breaker.
  - `false` Disables regex. Any Painless script containing a regular expression returns an error.
- `script.painless.regex.limit-factor` 静态配置，Limits the number of characters a regular expression in a Painless script can consider. Elasticsearch calculates this limit by multiplying the setting value by the script input’s character length.

## 集群级别的共享分配和路由设置

## 跨集群分片设置

## 服务发现和集群信息设置

## 字段数据缓存设置

## 健康诊断设置

## 索引生命周期管理设置



## 索引管理设置

- `action.auto_create_index` 动态配置，如果索引不存在则自动创建索引，默认为true
- `action.destructive_requires_name` 动态配置，如果设置为true则删除索引的时候必须指定索引名字，不能通过`_all`或者通配符来删除所有的索引，默认为true
- `cluster.indices.close.enable` 动态配置，可以关闭索引，默认为true
- `reindex.remote.whitelist` 静态配置，指定可以远程reindexed的主机
- `stack.templates.enabled` 动态配置，启用内置的索引和组件模板，默认为true

## 索引恢复设置

## 索引缓冲设置

## 证书设置

## 本地网关设置

## 日志设置

## 机器学习设置

## 监控设置

## 节点

### 节点角色

可以通过`elasticsearch.yml`配置文件中的`node.roles`来设置节点的角色，如果不配置这个属性，则一个节点会被赋予以下的角色：

- master 可以参与master的选举
- data 存储数据、提供查询搜索聚合等操作
- data_content
- data_hot
- data_warm
- data_cold
- data_froizen
- ingest
- ml
- remote_cluster_client
- transform

## 网络

默认只绑定到了localhost，如需需要远程访问则需要在`elasticsearch.yml`配置文件中配置`network.host`

- `network.host` 静态配置，设置节点的地址，默认是`_local_`
- `http.port` 静态配置，http端口，默认是9200到9300
- `transport.port` 静态配置，节点间通信端口，默认是9300到9400
- `network.bind_host` 静态配置，处理请求的网络地址
- `network.publish_host` 静态配置，节点间通信的网络地址
- `network.tcp.keep_alive` 静态配置
- `network.tcp.keep_idle` 静态配置
- `network.tcp.keep_interval` 静态配置
- `network.tcp.keep_count` 静态配置
- `network.tcp.no_delay` 静态配置
- `network.tcp.reuse_address` 静态配置
- `network.tcp.send_buffer_size` 静态配置
- `network.tcp.receive_buffer_size` 静态配置
- `http.host` 静态配置
- `http.bind_host` 静态配置
- `http.publish_host` 静态配置
- `http.publish_port`  静态配置
- `http.max_content_length` 静态配置
- `http.max_initial_line_length` 静态配置
- `http.max_header_size` 静态配置
- `http.compression`  静态配置
- `http.compression_level` 静态配置
- `http.cors.enabled`  静态配置
- `http.cors.allow-origin`  静态配置
- `http.cors.max-age`  静态配置
- `http.cors.allow-methods` 静态配置
- `http.cors.allow-headers`  静态配置
- `http.cors.allow-credentials`  静态配置
- `http.detailed_errors.enabled` 静态配置
- `http.pipelining.max_events` 静态配置
- `http.max_warning_header_count` 静态配置
- `http.max_warning_header_size` 静态配置
- `http.tcp.keep_alive` 静态配置
- `http.tcp.keep_idle` 静态配置
- `http.tcp.keep_interval` 静态配置
- `http.tcp.keep_count` 静态配置
- `http.tcp.no_delay` 静态配置
- `http.tcp.reuse_address` 静态配置
- `http.tcp.send_buffer_size` 静态配置
- `http.tcp.receive_buffer_size` 静态配置
- `http.client_stats.enabled` 静态配置
- `http.client_stats.closed_channels.max_count` 静态配置
- `http.client_stats.closed_channels.max_age` 静态配置
- `transport.host`  静态配置
- `transport.bind_host`  静态配置
- `transport.publish_host`  静态配置
- `transport.publish_port`  静态配置
- `transport.connect_timeout`  静态配置
- `transport.compress`  静态配置
- `transport.compression_scheme`  静态配置
- `transport.tcp.keep_alive`  静态配置
- `transport.tcp.keep_idle`  静态配置
- `transport.tcp.keep_interval`  静态配置
- `transport.tcp.keep_count`  静态配置
- `transport.tcp.no_delay`  静态配置
- `transport.tcp.reuse_address`  静态配置
- `transport.tcp.send_buffer_size`  静态配置
- `transport.tcp.receive_buffer_size`  静态配置
- `transport.ping_schedule`  静态配置

## 节点查询缓存设置

使用filter进行查询的时候数据会缓存到节点缓存中，每个节点只有一个查询缓存，被所有分片共享，使用LRU过期策略。默认缓存最大10000条，不超过堆内存的10%。

- `indices.queries.cache.size` 静态配置 控制缓存的内存的大小，可以是百分数（10%)也可以是准确的大小(512mb)，默认是10%
- `index.queries.cache.enabled` 静态配置，可以配置到每个索引上，控制是否启用查询缓存，默认是true

## 搜索设置

- `indices.query.bool.max_clause_count` 静态配置，一个查询可以包含的最大的子句，默认4096
- `search.max_buckets` 动态配置，一次响应中的最大的聚合的桶的个数，默认65536
- `indices.query.bool.max_nested_depth` 静态配置，布尔查询最大的嵌套深度，默认20

## 安全设置

## 共享请求缓存设置

## 快照和恢复设置

## Transforms设置

## 线程池

## Watcher设置



