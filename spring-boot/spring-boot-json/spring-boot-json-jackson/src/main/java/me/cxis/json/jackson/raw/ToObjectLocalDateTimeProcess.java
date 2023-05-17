package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import me.cxis.json.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 字符串反序列化为对象时LocalDateTime类型的处理，默认不支持LocalDateTime类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式
 */
public class ToObjectLocalDateTimeProcess {

    public static void main(String[] args) throws JsonProcessingException {

        yyyyMMddTHHmmss();

        timestamp();

        HHmmss();

        yyyyMMdd();

        yyyyMMddHHmmss();

        yyyyMMddHHmmssSSS();
    }


    private static void timestamp() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDateTime\":1674979995000}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDateTime类型时间戳：" + user);
    }

    private static void yyyyMMdd() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDateTime\":\"2023-01-29\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDateTime类型yyyy-MM-dd：" + user);
    }

    private static void HHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDateTime\":\"16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDateTime类型HH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDateTime\":\"2023-01-29 16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDateTime类型yyyy-MM-dd HH:mm:ss：" + user);
    }

    private static void yyyyMMddTHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDateTime\":\"2023-01-29T16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDateTime类型yyyy-MM-ddTHH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmssSSS() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":\"2023-01-29 16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDateTime类型yyyy-MM-dd HH:mm:ss.SSS：" + user);
    }
}
