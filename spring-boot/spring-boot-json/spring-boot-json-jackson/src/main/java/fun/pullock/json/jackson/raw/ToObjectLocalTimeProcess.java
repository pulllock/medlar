package fun.pullock.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import fun.pullock.json.model.User;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 字符串反序列化为对象时LocalTime类型的处理，默认不支持LocalTime类型，需要添加`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`依赖并设置格式
 */
public class ToObjectLocalTimeProcess {

    public static void main(String[] args) throws JsonProcessingException {

        yyyyMMddTHHmmss();

        HHmmss();

        yyyyMMdd();

        yyyyMMddHHmmss();

        yyyyMMddHHmmssSSS();

        timestamp();
    }


    private static void timestamp() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localTime\":1674979995000}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalTime类型时间戳：" + user);
    }

    private static void yyyyMMdd() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localTime\":\"2023-01-29\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalTime类型yyyy-MM-dd：" + user);
    }

    private static void HHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localTime\":\"16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalTime类型HH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localTime\":\"2023-01-29 16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalTime类型yyyy-MM-dd HH:mm:ss：" + user);
    }

    private static void yyyyMMddTHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localTime\":\"2023-01-29T16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalTime类型yyyy-MM-ddTHH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmssSSS() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"localDate\":\"2023-01-29 16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(
                LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        mapper.registerModule(javaTimeModule);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化LocalTime类型yyyy-MM-dd HH:mm:ss.SSS：" + user);
    }
}
