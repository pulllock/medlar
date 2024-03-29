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

analyzer包含三块：

- character filters（字符过滤器）对原始的文本流进行处理，可以添加、删除、修改字符，一个analyzer可以包含0个或者多个character filters。
- tokenizers（分词器）对字符流进行处理，将其分成单独的词，同时也会记录每个词的顺序和位置等，一个analyzer必须包含一个tokenizer。
- token filters（分词过滤器）对分完词的流进行处理，可以添加、删除、修改词，一个analyzer可以有0个或者多个token filters。

文本分析会在两个地方触发：

- 索引的时候，text类型的字段的值会触发文本分析
- 搜索的时候，在对text类型的字段进行搜索的时候，会对搜索的字符串进行文本分析

Stemming词干提取，将一个词还原成原形，stemmer token filters有两种：

- Algorithmic stemmers，基于一个规则集合来进行词干提取，有如下的token filters：
  - stemmer
  - kstem
  - porter_stem
  - snowball
- Dictionary stemmers，基于一个字典来查找词干

### 配置文本分析

Elasticsearch在文本分析时默认使用standard分析器。

#### 创建自定义分析器

一个分析器包含如下：

- 0或者多个字符过滤器character filters
- 一个分词器tokenizer
- 0或者多个分词过滤器token filters

自定义的分析器可以有如下的参数：

- type：分析器类型，自定义的使用custom或者不添加此参数
- tokenizer：分词器（必须）
- char_filter：字符过滤器（可选）
- filter：分词过滤器（可选）
- position_increment_gap：默认100

Elasticsearch在对文档进行索引的时候按照如下的顺序来使用分析器：

1. 在mapping中的字段上指定的analyzer参数
2. `analysis.analyzer.default`中配置的分析器
3. 如果上面两个都没有，则使用standard分析器

Elasticsearch在搜索的时候按照如下的顺序来使用分析器：

1. 在搜索参数中指定的analyzer参数的值
2. 在mapping中的字段上指定的search_analyzer参数
3. `analysis.analyzer.default_search`中配置的分析器
4. 在mapping中的字段上指定的analyzer参数
5. 如果上面4个都没有，则使用standard分析器

### 内置的分析器

- Standard Analyzer: standard
- Simple Analyzer: simple
- Whitespace Analyzer: whitespace
- Stop Analyzer: stop
- Keyword Analyzer: keyword
- Pattern Analyzer: pattern
- Language Analyzer: 指定语言，比如：english或者french
- Fingerprint Analyzer: fingerprint

### 分词器

分词器接收一个字符流并对其进行分词，将字符流分割成一个个独立的词，输出分词的流。

内置的分词器：

- Standard Tokenizer: standard
- Letter Tokenizer: letter
- Lowercase Tokenizer: lowercase
- Whitespace Tokenizer: whitespace
- UAX URL Email Tokenizer: uax_url_email
- Classic Tokenizer: classic
- Thai Tokenizer: thai
- N-Gram Tokenizer: ngram
- Edge N-Gram Tokenizer: edge_ngram
- Keyword Tokenizer: keyword
- Pattern Tokenizer: pattern
- Simple Pattern Tokenizer: simple_pattern
- Char Group Tokenizer: char_group
- Simple Pattern Split Tokenizer: simple_pattern_split
- Path Tokenizer: path_hierarchy

### 分词过滤器

分词过滤器可以接收一个分词的流，对分词进行修改、删除、添加操作

### 字符过滤器

字符过滤器用来处理字符流，之后会将处理后的字符流传给分词器，字符过滤器接受原始的文本字符流，可以添加、删除、修改字符。

内置的字符过滤器：

- HTML Strip Character Filter: html_strip
- Mapping Character Filter: character
- Pattern Replace Character Filter: pattern_replace

### Normalizers

Normalizer和分析器类似，但是Normalizer不会进行分词，Normalizer没有分词器，只有字符过滤器和分词过滤器，只对keyword类型字段有效

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

| JSON数据类型                                  | `"dynamic":"true"`      | `"dynamic":"runtime"` | 备注                                                                             |
| ----------------------------------------- | ----------------------- | --------------------- | ------------------------------------------------------------------------------ |
| `null`                                    | 不添加字段                   | 不添加字段                 |                                                                                |
| `true` or `false`                         | `boolean`               | `boolean`             |                                                                                |
| `double`                                  | `float`                 | `double`              |                                                                                |
| `long`                                    | `long`                  | `long`                |                                                                                |
| `object`                                  | `object`                | 不添加字段                 |                                                                                |
| `array`                                   | 依赖于数组中第一个不为null的值       | 依赖于数组中第一个不为null的值     |                                                                                |
| `string`如果匹配到`dynamic_date_formats`中配置的格式 | `date`                  | `date`                | `dynamic_date_formats`默认值：`["strict_date_optional_time","yyyy/MM/dd HH:mm:ss Z |
| `string`匹配到是数字                            | `float` or `long`       | `double` or `long`    |                                                                                |
| `string`如果不是日期也不是数组                       | `text`类型， `.keyword`子类型 | `keyword`             |                                                                                |

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

#### Aggregate metric

#### Alias

可以为字段设置别名，别名可以在搜索中使用

```json
PUT trips
{
  "mappings": {
    "properties": {
      "distance": {
        "type": "long"
      },
      "route_length_miles": {
        "type": "alias",
        "path": "distance" 
      },
      "transit_mode": {
        "type": "keyword"
      }
    }
  }
}

GET _search
{
  "query": {
    "range" : {
      "route_length_miles" : {
        "gte" : 39
      }
    }
  }
}
```

#### 数组

Elasticsearch中没有array数据类型，任何字段都可以有0个或者多个值，在数组中的值必须是相同的数据类型，往数组中添加的第一个值的数据类型决定了数组的类型

#### Binary类型

binary类型字段可以存储一个使用Base64编码的字符串，默认不会存储也不可搜索，binary类型的字段可以有如下的参数：

- `doc_values`：true或false，默认false
- `store`：true或false，默认false

#### Boolean类型

可表示为False的有：`false`、`"false"`、`""（空字符串）`

可表示为True的有：`true`、`"true"`

Boolean类型的字段可以有如下的参数：

- `doc_values`：true或false，默认false
- `index`：true或false，默认true
- `null_value`
- `on_script_error`
- `script`
- `store`：true或false，默认false
- `meta`

#### Completion类型

completion类型字段有如下的参数：

- `analyzer`：默认是simple
- `search_analyzer`：默认是`analyzer`的值
- `preserve_separators`：默认是true
- `preserve_position_increments`：默认是true
- `max_input_length`：默认50

#### Date类型

Elasticsearch中date类型可以是如下的：

- 按照日期格式存储的字符串
- 毫秒表示的数字类型
- 秒表示的数字类型

date类型数据在内部会被转换为UTC时间，并以毫秒表示的数字类型存储。date类型的格式可以手动指定，如果不指定则默认是：`strict_date_optional_time||epoch_millis`。date类型有如下的参数：

- `doc_values`：true或false，默认false
- `format`：默认是`strict_date_optional_time||epoch_millis`
- `locale`
- `ignore_malformed`：true或false，默认false
- `index`：true或false，默认true
- `null_value`
- `on_script_error`
- `script`
- `store`：true或false，默认false
- `meta`

#### Date nanoseconds类型

date_nanos将日期类型存储为纳秒，格式可以手动指定，如果不指定默认是：`strict_date_optional_time_nanos||epoch_millis`

#### Dense vector类型

dense_vector类型的字段存储数字类型的密集向量，不支持聚合和排序。

#### Flattened类型

object类型字段默认会将子字段分别分开进行映射和索引，使用flattened类型字段，可以将一个object类型字段整个的当作一个字段。flattened类型字段和keyword类型功能差不多，flattened类型字段目前有如下的查询类型：

- `term`, `terms`, and `terms_set`
- `prefix`
- `range`
- `match` and `multi_match`
- `query_string` and `simple_query_string`
- `exists`

flattened类型有如下的参数：

- depth_limit
- doc_values
- eager_global_ordinals
- ignore_above
- index
- index_options
- null_value
- similarity
- split_queries_on_whitespace

#### Geopoint类型

geo_point类型存储经纬度

#### Geoshape类型

geo_shape

#### Histogram类型

histogram

#### IP类型

ip

#### Join类型

join可以在同一个索引中创建父子关系

#### Keyword类型

包含如下的类型：

- keyword
- constant_keyword
- wildcard

keyword的参数：

- `doc_values`：true或false，默认true
- `eager_global_ordinals`，true或false，默认false
- `fields`
- `ignore_above`
- `index`：true或false，默认true
- `index_options`
- `meta`
- `norms`
- `null_value`
- `on_script_error`
- `script`
- `store`：true或false，默认false
- `similarity`
- `normalizer`
- `split_queries_on_whitespace`：true或false，默认false
- `time_series_dimension`

constant_keyword是在一个字段在索引中所有的文档中都是同一个值的情况使用，参数有如下：

- `meta`
- `value`

wildcard类型参数有如下：

- `null_value`
- `ignore_above`

#### Nested类型

nested类型是一种特殊的object类型，可以让对象类型的数组进行独立查询，参数有如下：

- `dynamic`：默认为true，可以选：true，false，strict
- `properties`
- `include_in_parent`：true或false，默认false
- `include_in_root`：true或false，默认false

每一个nested对象会被分开索引，如果有一个文档包含100个user对象，则会有101个lucene文档被创建。

- `index.mapping.nested_fields.limit`：一个索引中最大的nested类型的字段，默认50
- `index.mapping.nested_objects.limit`：一个文档中所有的nested类型的字段能包含的nested类型的对象的最大值，默认是10000

#### Numberic类型

类型包括：

- long
- integer
- short
- byte
- double
- float
- hal_float
- scaled_float
- unsigned_long

参数如下：

- `coerce`
- `doc_values`
- `ignore_malformed`
- `index`
- `meta`
- `null_value`
- `on_script_error`
- `script`
- `store`
- `time_series_dimension`
- `time_series_metric`

scaled_float的参数如下：

- `scaling_factor`

#### Object类型

object类型默认内部会将结构打平，变成一个key-value列表，参数如下：

- `dynamic`
- `enabled`
- `subobjects`
- `properties`

#### Percolator类型

percolator

#### Point类型

point

#### Range类型

类型有：

- integer_range
- float_range
- long_range
- double_range
- date_range
- ip_range

参数：

- `coerce`
- `index`
- `store`

#### Rank feature类型

rank_feature

#### Rank features类型

rank_features

#### Search-as-you-type

search_as_you_type

#### Shape类型

shape

#### Text类型

有如下类型：

- text
- match_only_text

text类型参数：

- `analyzer`
- `eager_global_ordinals`
- `fielddata`
- `fielddata_frequency_filter`
- `fields`
- `index`
- `index_options`
- `index_prefixes`
- `index_phrases`
- `norms`
- `position_increment_gap`
- `store`
- `search_analyzer`
- `search_quote_analyzer`
- `similarity`
- `term_vector`
- `meta`

match_only_text类型的参数有：

- `fields`
- `meta`

#### Token count类型

token_count

#### Unsigned long类型

unsigned_long

#### Version类型

version，是一个特殊的keyword类型字段，可以处理软件的版本号，参数如下：

- `meta`

### Metadata字段

每个文档都有一个元数据

#### ID类型的元数据字段

- `_index`：文档所属的索引
- `_id`：文档的ID

#### 文档source元数据字段

- `_source`：文档的原始的json数据
- `_size`：`_source`字段的字节大小

#### Doc count元数据字段

- `_doc_count`

#### Indexing元数据字段

- `_field_names`
- `_ignored`

#### Routing元数据字段

- `_routing`

#### 其他元数据字段

- `_meta`
- `_tier`

### Mapping参数

- `analyzer`
- `coerce`
- `copy_to`
- `doc_values`
- `dynamic`
- `eager_global_ordinals`
- `enabled`
- `fielddata`
- `fields`
- `format`
- `ignore_above`
- `ignore_malformed`
- `index_options`
- `index_phrases`
- `index_prefixes`
- `index`
- `meta`
- `normalizer`
- `norms`
- `null_value`
- `position_increment_gap`
- `properties`
- `search_analyzer`
- `similarity`
- `subobjects`
- `store`
- `term_vector`

### Mapping相关的限制设置

- `index.mapping.total_fields.limit`：一个索引中最大的字段数量，默认1000
- `index.mapping.depth.limit`：一个字段对大的深度，默认20
- `index.mapping.nested_fields.limit`：一个索引中最大的nested类型的字段，默认50
- `index.mapping.nested_objects.limit`：一个文档中所有的字段中能包含的nested类型的对象的最大值，默认10000
- `index.mapping.field_name_length.limit`：字段名长度，默认`Long.MAX_VALUE`
- `index.mapping.dimension_fields.limit`：默认16

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

# Query DSL

## Query和Filter

默认情况下Elasticsearch会将匹配的结果按照一个相关性分值进行排序，相关性分值是一个正的浮点数，在`_score`元数据字段中，相关性分值越高表示搜索结果越匹配。

查询语句分为query和filter两种方式，query方式会计算并返回相关性分值，而filter则不会计算和返回相关性分值，filter查询结果会被缓存。

## 复合查询

- bool
- boosting
- constant_score
- dis_max
- function_score

### Boolean query

bool query和Lucene的BooleanQuery一样，查询子句可以使用如下的：

- must
- filter：filter子句在filter上下文中执行，不会计算相关性分值
- should
- must_not：must_not子句在filter上下文中执行，不会计算相关性分值

### Boosting query

可以用来减少相关性分值，示例：

```
GET /_search
{
  "query": {
    "boosting": {
      "positive": {
        "term": {
          "text": "apple"
        }
      },
      "negative": {
        "term": {
          "text": "pie tart fruit crumble tree"
        }
      },
      "negative_boost": 0.5
    }
  }
}
```

参数有如下：

- positive：查询对象，必须，返回的文档必须匹配到该查询
- negative：查询对象，必须，该查询匹配到的文档会被减少相关性分值
- negative_boost：浮点型数字，必须，0到1.0之间的数字，用来减少的相关性分值

### Constant query

给filter查询指定一个常数的相关性分值，参数：

- filter：查询对象，必须
- boost：浮点型数字，可选，默认1.0

### Disjunction max query

dis_max可以实现多字段搜索的最佳字段匹配，可以使用多个字段查询文档，如果一个文档的多个字段都能匹配到，则该文档的相关性分值会使用分值最高的字段的分值，使用示例：

```
GET /_search
{
  "query": {
    "dis_max": {
      "queries": [
        { "term": { "title": "Quick pets" } },
        { "term": { "body": "Quick pets" } }
      ],
      "tie_breaker": 0.7
    }
  }
}
```

参数如下：

- queries：查询对象数组，必须
- tie_breaker：浮点型数字，可选，0到1.0之间的数字，可以减少查询的相关性分值，默认是0.0

### Function score query

elasticsearch的搜索结果是按照相关性分值进行排序的，相关性分值也可以通过`function_score`来进行控制。

单function示例：

```
GET /_search
{
  "query": {
    "function_score": {
      "query": { "match_all": {} },
      "boost": "5",
      "random_score": {}, 
      "boost_mode": "multiply"
    }
  }
}
```

多个function示例：

```
GET /_search
{
  "query": {
    "function_score": {
      "query": { "match_all": {} },
      "boost": "5", 
      "functions": [
        {
          "filter": { "match": { "test": "bar" } },
          "random_score": {}, 
          "weight": 23
        },
        {
          "filter": { "match": { "test": "cat" } },
          "weight": 42
        }
      ],
      "max_boost": 42,
      "score_mode": "max",
      "boost_mode": "multiply",
      "min_score": 42
    }
  }
}
```

参数：

- score_mode：决定计算出来的分数怎么合并
  - multiply：相乘，默认值
  - sum：相加
  - avg：平均
  - first：取第一个匹配的
  - max：最大
  - min：最小
- boost_mode：
  - multiply：query score和function score相乘，默认值
  - replace：只使用function score
  - sum：query score和function score相加
  - avg：平均
  - max：query score和function score取最大
  - min：query score和function score取最小

function_score提供如下几种score函数：

- script_score
- weight
- random_score
- field_value_factor
- decay functions：`gauss`, `linear`, `exp`

## 全文搜索

- intervals：允许用户精确控制查询词在文档中出现的先后顺序，实现了对terms顺序、terms之间的距离以及他们之间的包含关系的灵活控制
- match：全文搜索的标准查询，包括模糊匹配、短语或近似匹配
- match_bool_prefix：搜索中的词会按照term查询进行搜索，使用bool查询组合多个词的查询，最后一个词则是使用prefix查询
- match_phrase：和match查询类似，match_phrase用来匹配短语
- match_phrase_prefix：和match_phrase类似，但是最后一个词使用wildcard查询
- multi_match：支持多个字段的match查询
- combined_fields：支持搜索多个文本字段，就好像他们的内容被索引到一个组合字段一样
- query_string：支持Lucene的query string语法，允许在一个查询语句中使用多个特殊的条件关键字（AND、OR、NOT）对多个字段进行查询，专业人士使用，不适合开放给普通用户
- simple_query_string：比query_string更严格，更简单，适合开放给用户

### intervals

允许用户精确控制查询词在文档中出现的先后顺序，实现了对terms顺序、terms之间的距离以及他们之间的包含关系的灵活控制

使用示例：

```
POST _search
{
  "query": {
    "intervals" : {
      "my_text" : {
        "all_of" : {
          "ordered" : true,
          "intervals" : [
            {
              "match" : {
                "query" : "my favorite food",
                "max_gaps" : 0,
                "ordered" : true
              }
            },
            {
              "any_of" : {
                "intervals" : [
                  { "match" : { "query" : "hot water" } },
                  { "match" : { "query" : "cold porridge" } }
                ]
              }
            }
          ]
        }
      }
    }
  }
}
```

参数：

- field：要搜索的字段
  - match
    - query：要搜索的内容
    - max_gaps：匹配的词之间的最大距离，默认-1
    - ordered：默认false，如果为true则匹配的词需要按照指定的顺序
    - analyzer
    - filter
    - user_field
  - prefix
    - prefix
    - analyzer
    - user_field
  - wildcard
    - pattern
    - analyzer
    - user_field
  - fuzzy
    - term
    - prefix_length
    - transpositions
    - fuzziness
    - analyzer
    - use_field
  - all_of
    - intervals
    - max_gaps
    - ordered
    - filter
  - any_of
    - intervals
    - filter
  - filter
    - after
    - before
    - contained_by
    - containing
    - not_contained_by
    - not_containing
    - not_overlapping
    - overlapping
    - script

### match

全文搜索的标准查询，包括模糊匹配、短语或近似匹配，搜索条件会先被分析器进行分析。match查询是bool类型的查询，默认的operator是OR。

使用示例：

```
GET /_search
{
  "query": {
    "match": {
      "message": {
        "query": "this is a test"
      }
    }
  }
}
```

参数：

- field：要搜索的字段
  - query：要搜索的值，文本、数字、布尔值、日期
  - analyzer
  - auto_generate_synonyms_phrase_query：默认true
  - fuzziness
  - max_expansions：默认50
  - prefix_length：默认0
  - fuzzy_transpositions：默认true
  - fuzzy_rewrite
  - lenient：默认false
  - operator：
    - OR：默认
    - AND
  - minimum_should_match
  - zero_terms_query：
    - none：默认
    - all

### match_bool_prefix

match_bool_prefix会对搜索条件进行分析，对分析后的词构造一个bool查询，最后一个词则使用prefix查询。

使用示例：

```
GET /_search
{
  "query": {
    "match_bool_prefix" : {
      "message" : "quick brown f"
    }
  }
}
```

这个查询类似于如下的bool查询：

```
GET /_search
{
  "query": {
    "bool" : {
      "should": [
        { "term": { "message": "quick" }},
        { "term": { "message": "brown" }},
        { "prefix": { "message": "f"}}
      ]
    }
  }
}
```

### match_phrase

和match查询类似，match_phrase用来匹配短语

使用示例：

```
GET /_search
{
  "query": {
    "match_phrase": {
      "message": "this is a test"
    }
  }
}
```

### match_phrase_prefix

和match_phrase类似，但是最后一个词使用wildcard查询

使用示例：

```
GET /_search
{
  "query": {
    "match_phrase_prefix": {
      "message": {
        "query": "quick brown f"
      }
    }
  }
}
```

参数：

- field：要搜索的字段
  - query：要搜索的值
  - analyzer
  - max_expansions：默认50
  - slop：默认0
  - zero_terms_query
    - none：默认
    - all

### combined_fields

支持搜索多个文本字段，就好像他们的内容被索引到一个组合字段一样，combined_fields需要搜索的所有字段都是一样的analyzer，multi_match则不需要所有字段都是一样的analyzer

使用示例：

```
GET /_search
{
  "query": {
    "combined_fields" : {
      "query":      "database systems",
      "fields":     [ "title", "abstract", "body"],
      "operator":   "and"
    }
  }
}
```

参数：

- fields：要查询的字段
  - query：要查询的条件
  - auto_generate_synonyms_phrase_query
  - operator
    - or：默认
    - and
  - minimum_should_match
  - zero_terms_query
    - none：默认
    - all

### multi_match

支持多个字段的match查询，搜索的字段名字可以使用通配符来匹配，可以使用`^`来给字段改变相关性分值

使用示例：

```
GET /_search
{
  "query": {
    "multi_match" : {
      "query":    "this is a test", 
      "fields": [ "subject", "message" ] 
    }
  }
}
```

multi_match的type参数可以有如下的选择：

- best_fields：默认，在所有字段中查询，但是使用最匹配的字段的分值
- most_fields：在所有字段中查询，并将所有的查询到的字段的分值合并
- cross_fields
- phrase
- phrase_prefix
- bool_prefix

### query_string

支持Lucene的query string语法，允许在一个查询语句中使用多个特殊的条件关键字（AND、OR、NOT）对多个字段进行查询，专业人士使用，不适合开放给普通用户

使用示例：

```
GET /_search
{
  "query": {
    "query_string": {
      "query": "(new york city) OR (big apple)",
      "default_field": "content"
    }
  }
}
```

### simple_query_string

比query_string更严格，更简单，适合开放给用户

使用示例：

```
GET /_search
{
  "query": {
    "simple_query_string" : {
        "query": "\"fried eggs\" +(eggplant | potato) -frittata",
        "fields": ["title^5", "body"],
        "default_operator": "and"
    }
  }
}
```

## Term级别查询

term查询不会对搜索的条件进行分析。

- exists：只查找指定字段有索引值的文档
- fuzzy：模糊查询
- ids：id查询
- prefix：前缀查询
- range：范围查询
- regexp：正则查询
- term：精确匹配词查询
- terms：包含一个或多个精确匹配词查询
- terms_set：可以指定能匹配的最小数量
- wildcard：通配符查询

### exists

只查找指定字段有索引值的文档，一个文档的字段可能没有索引值，可能有的原因有如下：

- 字段的值是null或者`[]`
- 字段的是否索引设置为了false：`"index":false`
- 设置了`ignore_above`，字段的值的长度超过了该值
- 字段的值有问题，但是设置了`ignore_malformed`，这个字段的值会被丢弃或不进行索引

使用示例：

```
GET /_search
{
  "query": {
    "exists": {
      "field": "user"
    }
  }
}
```

参数：

- field: 比如，要搜索的字段名字

### fuzzy

模糊查询，可以在输入错误的时候也能搜索到数据

使用示例，简单查询：

```
GET /_search
{
  "query": {
    "fuzzy": {
      "user.id": {
        "value": "ki"
      }
    }
  }
}
```

使用示例，高级查询：

```
GET /_search
{
  "query": {
    "fuzzy": {
      "user.id": {
        "value": "ki",
        "fuzziness": "AUTO",
        "max_expansions": 50,
        "prefix_length": 0,
        "transpositions": true,
        "rewrite": "constant_score"
      }
    }
  }
}
```

参数：

- field：要搜索的字段
  - value：需要搜索的值
  - fuzziness
  - max_expansions
  - prefix_length
  - transpositions
  - rewrite

### ids

id查询，使用示例：

```
GET /_search
{
  "query": {
    "ids" : {
      "values" : ["1", "4", "100"]
    }
  }
}
```

### prefix

前缀查询，使用示例：

```
GET /_search
{
  "query": {
    "prefix": {
      "user.id": {
        "value": "ki"
      }
    }
  }
}
```

参数：

- field：要搜索的字段
  - value：要搜索的值
  - rewrite
  - case_insensitive

### range

范围查询，使用示例：

```
GET /_search
{
  "query": {
    "range": {
      "age": {
        "gte": 10,
        "lte": 20,
        "boost": 2.0
      }
    }
  }
}
```

参数：

- field：要搜索的字段
  - gt：大于
  - gte：大于等于
  - lt：小于
  - let：小于等于
  - format：日期的格式
  - relation：
    - INTERSECTS：默认
    - CONTAINS
    - WITHIN
  - time_zone
  - boost：浮点数，增加或减少相关性分值，默认为1.0

### regexp

正则查询，使用示例：

```
GET /_search
{
  "query": {
    "regexp": {
      "user.id": {
        "value": "k.*y",
        "flags": "ALL",
        "case_insensitive": true,
        "max_determinized_states": 10000,
        "rewrite": "constant_score"
      }
    }
  }
}
```

### term

精确匹配词查询，不要在text类型的字段上使用term查询，要搜索text类型的字段使用match查询。

使用示例：

```
GET /_search
{
  "query": {
    "term": {
      "user.id": {
        "value": "kimchy",
        "boost": 1.0
      }
    }
  }
}
```

参数：

- field：要查询的字段
  - value：要查询的值
  - boost：浮点数，增加或者减少相关性分值，默认是1.0，0到1.0之间的值用来减少相关性分值，大于1.0的值用来增加相关性分值
  - case_insensitive

### terms

可以使用多个词进行精确匹配查询

使用示例：

```
GET /_search
{
  "query": {
    "terms": {
      "user.id": [ "kimchy", "elkbee" ],
      "boost": 1.0
    }
  }
}
```

参数：

- field：要搜索的字段，值是数组
- boost：浮点数，可以用来增加或者减少相关性分值

### terms_set

可以指定能匹配的最小数量

使用示例：

```
GET /job-candidates/_search
{
  "query": {
    "terms_set": {
      "programming_languages": {
        "terms": [ "c++", "java", "php" ],
        "minimum_should_match_field": "required_matches"
      }
    }
  }
}
```

### wildcard

通配符查询

使用示例：

```
GET /_search
{
  "query": {
    "wildcard": {
      "user.id": {
        "value": "ki*y",
        "boost": 1.0,
        "rewrite": "constant_score"
      }
    }
  }
}
```

参数：

- field：要搜索的字段
  - boost：浮点型数值，可以增加或者减少相关性分值
  - case_insensitive
  - rewrite
  - value：支持如下两种通配符：
    - `?`：匹配单个字符
    - `*`：匹配0或多个字符
  - wildcard：value的别名参数

## Geo

- geo_bounding_box
- geo_distance
- geo_grid
- geo_polygon
- geo_shape

## Shape

- shape

## Joining

有两种形式的join：

- nested
- has_child和has_parent：需join关系的字段需要在同一个索引的不同文档之间

### nested

使用示例：

```
GET /my-index-000001/_search
{
  "query": {
    "nested": {
      "path": "obj1",
      "query": {
        "bool": {
          "must": [
            { "match": { "obj1.name": "blue" } },
            { "range": { "obj1.count": { "gt": 5 } } }
          ]
        }
      },
      "score_mode": "avg"
    }
  }
}
```

参数：

- path：要搜索的nested对象的路径
- query：要搜索的内容
- score_mode
  - avg：默认
  - max
  - min
  - none
  - sum
- ignore_unmapped：默认false

### has_child

创建索引的mapping：

```
PUT /my-index-000001
{
  "mappings": {
    "properties": {
      "my-join-field": {
        "type": "join",
        "relations": {
          "parent": "child"
        }
      }
    }
  }
}
```

查询示例：

```
GET /_search
{
  "query": {
    "has_child": {
      "type": "child",
      "query": {
        "match_all": {}
      },
      "max_children": 10,
      "min_children": 2,
      "score_mode": "min"
    }
  }
}
```

参数：

- type
- query
- ignore_unmapped：默认false
- max_children
- min_children
- score_mode
  - none：默认
  - avg
  - max
  - min
  - sum

### has_parent

创建索引的mapping：

```
PUT /my-index-000001
{
  "mappings": {
    "properties": {
      "my-join-field": {
        "type": "join",
        "relations": {
          "parent": "child"
        }
      },
      "tag": {
        "type": "keyword"
      }
    }
  }
}
```

查询示例：

```
GET /my-index-000001/_search
{
  "query": {
    "has_parent": {
      "parent_type": "parent",
      "query": {
        "term": {
          "tag": {
            "value": "Elasticsearch"
          }
        }
      }
    }
  }
}
```

参数：

- parent_type
- query
- score：默认false
- ignore_unmapped：默认false

### parent_id

使用示例：

```
GET /my-index-000001/_search
{
  "query": {
      "parent_id": {
          "type": "my-child",
          "id": "1"
      }
  }
}
```

参数：

- type
- id
- ignore_unmapped：默认false

## match_all

最简单的查询，会匹配到所有的文档，匹配到的文档的相关性分值_score都是1.0，查询的时候可以使用boost来指定相关性分值

## match_none

和match_all相反，不会匹配到任何文档

## Span查询

同段、同句搜索的场景可以使用span查询，有如下的查询：

- span_containing
- span_field_masking
- span_first
- span_multi
- span_near
- span_not
- span_or
- span_term
- span_within

## 其他的一些查询

- distance_feature
- more_like_this
- percolate
- rank_feature
- script
- script_score
- wrapper
- pinned

# Aggregations

有三种分类：

- Metric：指标聚合，对字段进行统计分析，数学运算，比如sum、avg
- Bucket：桶聚合，满足特定条件的文档的集合
- Pipeline：管道聚合，对聚合结果进行二次聚合

使用示例：

```
GET /my-index-000001/_search
{
  "aggs": {
    "my-agg-name": {
      "terms": {
        "field": "my-field"
      }
    }
  }
}
```

## Bucket aggregations

- adjacency matrix
- auto-interval date histogram
- categorize text
- children
- composite
- date histogram
- date range
- diversified sampler
- filter
- filters
- frequent items
- geo-distance
- geohash grid
- geohex grid
- geotile grid
- global
- histogram
- ip prefix
- ip range
- missing
- multi terms
- nested
- parent
- random sampler
- range
- rare terms
- reverse nested
- sampler
- significant terms
- significant text
- terms
- variable width histogram
- subtleties of bucketing range fields

## Metrics aggregations

- avg
- boxplot
- cardinality
- extended stats
- geo-bounds
- geo-centroid
- geo-line
- matrix stats
- max
- median absolute deviation
- min
- percentile ranks
- percentiles
- rate
- scripted metric
- stats
- string stats
- sum
- t-test
- top hits
- top metrics
- value count
- weighted avg

### avg

可以用在数值型的字段或者histogram类型的字段上

使用示例：

```
POST /exams/_search?size=0
{
  "aggs": {
    "avg_grade": { "avg": { "field": "grade" } }
  }
}
```

参数：

- field：要进行聚合的字段
- missing：如果要聚合的字段没有值，可以使用该字段指定一个默认的值

## Pipeline aggregations

- average bucket
- bucket script
- bucket count k-s test
- bucket correlation
- bucket selector
- bucket sort
- change point
- cumulative cardinality
- cumulative sum
- derivative
- extended stats bucket
- inference bucket
- max bucket
- min bucket
- moving function
- moving percentiles
- normalize
- percentiles bucket
- serial differencing
- stats bucket
- sum bucket

# 命令行工具

- elasticsearch-certgen
- elasticsearch-certutil
- elasticsearch-create-enrollment-token
- elasticsearch-croneval
- elasticsearch-keystore
- elasticsearch-node
- elasticsearch-reconfigure-node
- elasticsearch-reset-password
- elasticsearch-saml-metadata
- elasticsearch-setup-passwords
- elasticsearch-shard
- elasticsearch-syskeygen
- elasticsearch-users

# REST APIS

## 通用的选项

- `pretty=true`：返回的json结果会被格式化，只在开发和调试的时候使用
- `format=yaml`：返回yaml格式的结果
- `human=false`：可读的格式，默认是false
- `filter_path=took,hits.hits._id,hits.hits._score`：可以控制返回的数据，使用逗号分割，也支持使用`*`和`**`通配符来制定字段的名字
- `flat_settings=true`：控制返回的settings结果以平铺的方式展示
- `error_trace=true`：可返回错误的stack trace信息

## Document APIs

单文档APIs：

- Index
- Get
- Delete
- Update

多文档APIs：

- Multi get
- Bulk
- Delete by query
- Update by query
- Reindex

### Index API

#### 请求

- `PUT /<target>/_doc/<_id>`
- `POST /<target>/_doc/`
- `PUT /<target>/_create/<_id>`
- `POST /<target>/_create/<_id>`

#### Path参数

- `<target>`：index或者data stream的名字
- `<_id>`：文档的唯一id

#### Query参数

- if_seq_no：如果文档是指定的sq_no才执行操作
- if_primary_term：如果文档的primary term是指定的值时才执行操作
- op_type：
  - index：默认
  - create：指定的id的文档必须不存在，否则操作失败
- pipeline：指定pipline的ID
- refresh：
  - true：
  - false：默认
  - wait_for
- routing
- timeout：默认1m
- version
- version_type：
  - external
  - external_gte
- wait_for_active_shards：默认1
- require_alias：
  - true
  - false：默认

#### Request body

- `<field>`：json格式的文档数据

#### Response body

- `_shards`
- `_shards.total`
- `_shards.successful`
- `_shards.failed`
- `_index`
- `_type`
- `_id`
- `_version`
- `_seq_no`
- `_primary_term`
- `result`

### Get API

#### Request

- `GET <index>/_doc/<_id>`

- `HEAD <index>/_doc/<_id>`

- `GET <index>/_source/<_id>`

- `HEAD <index>/_source/<_id>`

#### Path参数

- `<index>`

- `<_id>`

#### Query参数

- `preference`

- `realtime`

- `refresh`

- `routing`

- `stored_fields`

- `_source`

- `_source_excludes`

- `_source_includes`

- `version`

- `version_type`

#### Response body

- `_index`

- `_id`

- `_version`

- `_seq_no`

- `_primary_term`

- `found`

- `_routing`

- `_source`

- `_fields`

### Delete API

Request

- `DELETE /<index>/_doc/<_id>`

#### Path参数

- `<index>`

- `<_id>`

#### Query参数

- `if_seq_no`

- `if_primary_term`

- `refresh`

- `routing`

- `timeout`

- `version`

- `version_type`

- `wait_for_active_shards`

### Delete by query API

#### Request

- `POST /<target>/_delete_by_query`

#### Path parameters

- `<target>`

#### Query parameters

- `allow_no_indices`

- `analyzer`

- `analyze_wildcard`

- `conflicts`

- `default_operator`

- `df`

- `expand_wildcards`
  
  - `all`
  
  - `open`
  
  - `closed`
  
  - `hidden`
  
  - `none`

- `from`

- `ignore_unavailable`

- `lenient`

- `max_docs`

- `preference`

- `q`

- `request_cache`

- `refresh`

- `requests_per_second`

- `routing`

- `scroll`

- `scroll_size`

- `search_type`
  
  - `query_then_fetch`
  
  - `dfs_query_then_fetch`

- `search_timeout`

- `slices`

- `sort`

- `stats`

- `terminate_after`

- `timeout`

- `version`

- `wait_for_active_shards`

#### Request body

- `query`

#### Response body

- `took`

- `timed_out`

- `total`

- `deleted`

- `batches`

- `version_conflicts`

- `noops`

- `retries`

- `throttled_millis`

- `requests_per_second`

- `throttled_until_millis`

- `failures`

### Update API

#### Request

- `POST /<index>/_update/<_id>`

#### Path parameters

- `<index>`

- `<_id>`

#### Query parameters

- `if_seq_no`

- `if_primary_term`

- `lang`

- `require_alias`

- `refresh`

- `retry_on_conflict`

- `routing`

- `_source`

- `_source_excludes`

- `_source_includes`

- `timeout`

- `wait_for_active_shards`

### Update By Query API

#### Request

- `POST /<target>/_update_by_query`

#### Path parameters

- `<target>`

#### Query parameters

- `allow_no_indices`

- `analyzer`

- `analyze_wildcard`

- `conflicts`

- `default_operator`

- `df`

- `expand_wildcards`
  
  - `all`
  
  - `open`
  
  - `closed`
  
  - `hidden`
  
  - `none`

- `from`
- `ignore_unavailable`

- `lenient`

- `max_docs`

- `pipeline`

- `preference`

- `q`

- `request_cache`

- `refresh`

- `requests_per_second`

- `routing`

- `scroll`

- `scroll_size`

- `search_type`
  
  - `query_then_fetch`
  
  - `dfs_query_then_fetch`

- `search_timeout`

- `slices`

- `sort`

- `stats`

- `terminate_after`

- `timeout`

- `version`

- `wait_for_active_shards`

#### Request body

- `query`

#### Response body

- `took`

- `timed_out`

- `total`

- `updated`

- `deleted`

- `batches`

- `version_conflicts`

- `noops`

- `retries`

- `throttled_millis`

- `requests_per_second`

- `throttled_until_millis`

- `failures`

### Multi get (mget) API

#### Request

- `GET /_mget`

- `GET /<index>/_mget`

#### Path parameters

- `<index>`

#### Query parameters

- `preference`

- `realtime`

- `refresh`

- `routing`

- `stored_fields`

- `_source`

- `_source_excludes`

- `_source_includes`

#### Request body

- `docs`
  
  - `_id`
  
  - `_index`
  
  - `routing`
  
  - `_source`
    
    - `source_include`
    
    - `source_exclude`
  
  - `_stored_fields`

- `ids`

### Bulk API

#### Request

- `POST /_bulk`

- `POST /<target>/_bulk`

#### Path parameters

- `<target>`

#### Query parameters

- `pipeline`

- `refresh`

- `require_alias`

- `routing`

- `_source`

- `_source_excludes`

- `_source_includes`

- `timeout`

- `wait_for_active_shards`

#### Request body

- `create`
  
  - `_index`
  
  - `_id`
  
  - `require_alias`
  
  - `dynamic_templates`

- `delete`
  
  - `_index`
  
  - `_id`
  
  - `require_alias`

- `index`
  
  - `_index`
  
  - `_id`
  
  - `require_alias`
  
  - `dynamic_templates`

- `update`
  
  - `_index`
  
  - `_id`
  
  - `require_alias`

- `doc`

- `<fields>`

#### Response body

- `took`

- `errors`

- `items`
  
  - `<action>`：create, delete, index, update
    
    - `_index`
    
    - `_id`
    
    - `_version`
    
    - `result`
    
    - `_shards`
      
      - `total`
      
      - `successful`
      
      - `failed`
    
    - `_seq_no`
    
    - `_primary_term`
    
    - `status`
    
    - `error`
      
      - `type`
      
      - `reason`
      
      - `index_uuid`
      
      - `shard`
      
      - `index`


