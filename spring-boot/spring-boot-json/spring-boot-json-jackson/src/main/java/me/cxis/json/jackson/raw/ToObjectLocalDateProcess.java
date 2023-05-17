package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import me.cxis.json.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 字符串反序列化为对象时LocalDate类型的处理，默认不支持LocalDate类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式
 */
public class ToObjectLocalDateProcess {

    public static void main(String[] args) throws JsonProcessingException {
        yyyyMMdd();

        yyyyMMddTHHmmss();

        yyyyMMddHHmmss();

        yyyyMMddHHmmssSSS();

        timestamp();

        HHmmss();
    }


    private static void timestamp() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":1674979995000}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDate类型时间戳：" + user);
    }

    private static void yyyyMMdd() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":\"2023-01-29\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDate类型yyyy-MM-dd：" + user);
    }

    private static void HHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":\"16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDate类型HH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":\"2023-01-29 16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDate类型yyyy-MM-dd HH:mm:ss：" + user);
    }

    private static void yyyyMMddTHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":\"2023-01-29T16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDate类型yyyy-MM-ddTHH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmssSSS() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":\"2023-01-29 16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalDate类型yyyy-MM-dd HH:mm:ss.SSS：" + user);
    }
}
