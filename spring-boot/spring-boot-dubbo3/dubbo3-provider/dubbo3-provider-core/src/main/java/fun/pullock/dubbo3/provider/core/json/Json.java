package fun.pullock.dubbo3.provider.core.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Json {

  private static final String LOCAL_DATE = "yyyy-MM-dd";

  private static final String LOCAL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

  private static final ObjectMapper MAPPER;

  static {
    MAPPER = new ObjectMapper();

    // 配置反序列化时允许未知属性
    MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    MAPPER.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
    MAPPER.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

    // 配置序列化时允许空Bean
    MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    // 配置序列化时只包含不为空的字段
    MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    // 配置反序列化时开启将浮点数解析成BigDecimal对象，不开启的时候默认会解析成Double对象
    MAPPER.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

    // Java日期格式序列化和反序列化配置
    JavaTimeModule javaTimeModule = new JavaTimeModule();

    // 更改LocalDate类型字段序列化的格式为yyyy-MM-dd
    javaTimeModule.addSerializer(
        LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE)));

    // 更改LocalDate类型字段反序列化的格式为yyyy-MM-dd
    javaTimeModule.addDeserializer(
        LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE)));

    // 更改LocalDateTime类型字段序列化的格式为yyyy-MM-dd HH:mm:ss
    javaTimeModule.addSerializer(
        LocalDateTime.class,
        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME)));

    // 更改LocalDateTime类型字段反序列化的格式为yyyy-MM-dd HH:mm:ss
    javaTimeModule.addDeserializer(
        LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME)));

    MAPPER.registerModule(javaTimeModule);
  }

  public static String toJsonString(Object object) {
    try {
      return MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static ObjectNode parseObject(String json) {
    if (json == null || json.length() == 0) {
      return null;
    }

    try {
      return (ObjectNode) MAPPER.readTree(json);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("can not cast to ObjectNode.", e);
    }
  }

  public static <T> T parseObject(String json, Class<T> clazz) {
    if (json == null || json.length() == 0) {
      return null;
    }

    try {
      return MAPPER.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> List<T> parseArray(String json, Class<T> clazz) {
    if (json == null || json.length() == 0) {
      return null;
    }

    try {
      JavaType type = MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
      return MAPPER.readValue(json, type);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
