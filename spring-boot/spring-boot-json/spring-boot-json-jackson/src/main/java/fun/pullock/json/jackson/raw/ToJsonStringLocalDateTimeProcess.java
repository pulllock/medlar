package fun.pullock.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import fun.pullock.json.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 序列化为字符串时对LocalDateTime类型的处理，默认不支持LocalDateTime类型，需要添加对应依赖并设置格式
 */
public class ToJsonStringLocalDateTimeProcess {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("test-username");
        user.setLocalDateTime(LocalDateTime.now());

        defaultLocalDateTime(user);
        customFormatLocalDateTime(user);
    }

    private static void defaultLocalDateTime(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalDateTime.class,
                new LocalDateTimeSerializer(null)
        );
        mapper.registerModule(javaTimeModule);
        System.out.println("jackson默认对LocalDateTime类型的处理：" + mapper.writeValueAsString(user));
    }

    private static void customFormatLocalDateTime(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd"))
        );
        mapper.registerModule(javaTimeModule);
        System.out.println("jackson自定义LocalDateTime类型格式：" + mapper.writeValueAsString(user));
    }
}
