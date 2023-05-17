package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import me.cxis.json.model.User;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 序列化为字符串时对LocalTime类型的处理，默认不支持LocalTime类型，需要添加对应依赖并设置格式
 */
public class ToJsonStringLocalTimeProcess {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("test-username");
        user.setLocalTime(LocalTime.now());

        defaultLocalTime(user);

        customFormatLocalTime(user);
    }

    private static void defaultLocalTime(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalTime.class,
                new LocalTimeSerializer(null)
        );
        mapper.registerModule(javaTimeModule);
        System.out.println("jackson默认对LocalTime类型的处理：" + mapper.writeValueAsString(user));
    }

    private static void customFormatLocalTime(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss"))
        );
        mapper.registerModule(javaTimeModule);
        System.out.println("jackson自定义LocalTime类型格式：" + mapper.writeValueAsString(user));
    }
}
