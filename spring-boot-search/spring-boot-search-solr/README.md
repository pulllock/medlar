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

# Schema

```
<schema>
  <types>
    <fieldType>
  <fields>
    <field>
  <copyField>
  <dynamicField>
  <similarity>
  <uniqueKey>
</schema>
```

# Fields

```
<field name="price" type="float" default="0.0" indexed="true" stored="true"/>
```

| Property                 | Required | Default |
| ------------------------ | -------- | ------- |
| name                     | required | none    |
| type                     | required | none    |
| default                  | optional | none    |
| indexed                  | optional | true    |
| stored                   | optional | true    |
| docValues                | optional | false   |
| sortMissingFirst         | optional | false   |
| sortMissingLast          | optional | false   |
| multiValued              | optional | false   |
| uninvertible             | optional | true    |
| omitNorms                | optional | *       |
| omitTermFreqAndPositions | optional | *       |
| omitPositions            | optional | *       |
| termVectors              | optional | false   |
| termPositions            | optional | false   |
| termOffsets              | optional | false   |
| termPayloads             | optional | false   |
| required                 | optional | false   |
| useDocValuesAsStored     | optional | true    |
| large                    | optional | false   |

## Field Types

```xml
<fieldType name="text_general" class="solr.TextField" positionIncrementGap="100"> 
  <analyzer type="index"> 
    <tokenizer class="solr.StandardTokenizerFactory"/>
    <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
    <!-- in this example, we will only use synonyms at query time
    <filter class="solr.SynonymFilterFactory" synonyms="index_synonyms.txt" ignoreCase="true" expand="false"/>
    -->
    <filter class="solr.LowerCaseFilterFactory"/>
  </analyzer>
  <analyzer type="query">
    <tokenizer class="solr.StandardTokenizerFactory"/>
    <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
    <filter class="solr.SynonymFilterFactory" synonyms="synonyms.txt" ignoreCase="true" expand="true"/>
    <filter class="solr.LowerCaseFilterFactory"/>
  </analyzer>
</fieldType>
```

| Property                  | Required | Default      | Values                                                   |
| ------------------------- | -------- | ------------ | -------------------------------------------------------- |
| name                      | required | none         |                                                          |
| class                     | required | none         |                                                          |
| positionIncrementGap      | optional | none         |                                                          |
| autoGeneratePhraseQueries | optional | true         |                                                          |
| synonymQueryStyle         | optional | as_same_term | - as_same_term<br />- pick_best<br />- as_distinct_terms |
| enableGraphQueries        | optional | true         |                                                          |
| indexed                   | optional | true         |                                                          |
| stored                    | optional | true         |                                                          |
| docValues                 | optional | false        |                                                          |
| docValuesFormat           | optional | none         |                                                          |
| docValuesFormat           | optional | none         |                                                          |
| sortMissingFirst          | optional | false        |                                                          |
| sortMissingLast           | optional | false        |                                                          |
| multiValued               | optional | false        |                                                          |
| uninvertible              | optional | true         |                                                          |
| omitNorms                 | optional | *            |                                                          |
| omitTermFreqAndPositions  | optional | *            |                                                          |
| omitPositions             | optional | *            |                                                          |
| termVectors               | optional | false        |                                                          |
| termPositions             | optional | false        |                                                          |
| termOffsets               | optional | false        |                                                          |
| termPayloads              | optional | false        |                                                          |
| required                  | optional | false        |                                                          |
| useDocValuesAsStored      | optional | true         |                                                          |
| large                     | optional | false        |                                                          |

### Field Types

| Class                               |
| :---------------------------------- |
| BBoxField                           |
| BinaryField                         |
| BoolField                           |
| CollationField                      |
| CurrencyFieldType                   |
| DateRangeField                      |
| DenseVectorField                    |
| DatePointField                      |
| DoublePointField                    |
| ExternalFileField                   |
| EnumFieldType                       |
| FloatPointField                     |
| ICUCollationField                   |
| IntPointField                       |
| LatLonPointSpatialField             |
| LongPointField                      |
| NestPathField                       |
| PointType                           |
| PreAnalyzedField                    |
| RandomSortField                     |
| RankField                           |
| RptWithGeometrySpatialField         |
| SortableTextField                   |
| SpatialRecursivePrefixTreeFieldType |
| StrField                            |
| TextField                           |
| UUIDField                           |

# Analysis

- analyzers
- tokenizers
- filters