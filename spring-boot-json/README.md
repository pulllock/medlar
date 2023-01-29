# spring-boot-json

# 对比

## 序列化为字符串时处理null值

- fastjson默认过滤null值，可通过设置`SerializerFeature.WriteMapNullValue`来保留null值
- jackson默认保留null值，可通过设置`objectMapper.setSerializationInclusion(Include.NON_NULL);`来过滤null值

fastjson示例：

```json
{"userName":"test-username"}
```

jackson示例：

```json
{"userName":"test-username","password":null,"age":null,"active":null}
```

## 字符串反序列化为对象时处理未知属性

- fastjson默认会忽略未知属性，可通过设置`Feature.IgnoreNotMatch`来启用遇到未知属性报错
- jackson默认有未知属性时会报错，可通过设置`mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);`来忽略未知属性

## 序列化为字符串时对boolean类型字段的处理

- fastjson会将boolean类型字段的开头的is去掉
- jackson会将boolean类型字段的开头的is去掉

## 字符串反序列化为对象时对没有引号的字段的处理

- fastjson默认支持字段名没有引号
- jackson默认不支持字段名没有引号，可通过设置`mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);`来支持字段名没有引号

## 序列化为字符串时对transient属性的处理

- fastjson默认会忽略transient属性
- jackson默认会序列化transient属性，可通过设置`mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);`来忽略transient属性

## 序列化为字符串时对Date类型的处理

- fastjson默认为时间戳
- jackson默认为时间戳

## 序列化为字符串时对LocalDate类型的处理

- fastjson默认为yyyy-MM-dd格式
- jackson默认不支持LocalDate类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式，如果不设置格式，默认是如下格式：`[15,59,56,631000000]`

## 序列化为字符串时对LocalTime类型的处理

- fastjson默认为HH:mm:ss.SSS格式
- jackson默认不支持LocalTime类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式，如果不设置格式，默认是如下格式：`[2023,1,29]`

## 序列化为字符串时对LocalDateTime类型的处理

- fastjson默认为yyyy-MM-ddTHH:mm:ss.SSS格式
- jackson默认不支持LocalDateTime类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式，如果不设置格式，默认是如下格式`[2023,1,29,15,55,33,43000000]`

## 字符串反序列化为对象时Date类型的处理

- fastjson默认可以处理：yyyy-MM-dd、yyyy-MM-dd HH:mm:ss、yyyy-MM-ddTHH:mm:ss、毫秒时间戳等
- jackson默认可以处理：yyyy-MM-dd、yyyy-MM-ddTHH:mm:ss、毫秒时间戳等

## 字符串反序列化为对象时LocalDate类型的处理

- fastjson默认可以处理：yyyy-MM-dd、yyyy-MM-ddTHH:mm:ss、毫秒时间戳等
- jackson默认不支持LocalDate类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式

## 字符串反序列化为对象时LocalTime类型的处理

- fastjson默认可以处理：yyyy-MM-ddTHH:mm:ss、HH:mm:ss、毫秒时间戳等
- jackson默认不支持LocalTime类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式

## 字符串反序列化为对象时LocalDateTime类型的处理

- fastjson默认可以处理：yyyy-MM-dd、yyyy-MM-ddTHH:mm:ss、yyyy-MM-dd HH:mm:ss、毫秒时间戳等
- jackson默认不支持LocalDateTime类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式

# jackson

## 日期的序列化和反序列化

- LocalDateTimeSerializer默认支持`[yyyy, MM, dd, HH, mm, ss]`格式的序列化
- LocalDateDeserializer默认支持`yyyy-MM-ddTHH:mm:ss`和`[yyyy, MM, dd, HH, mm, ss]`格式的反序列化