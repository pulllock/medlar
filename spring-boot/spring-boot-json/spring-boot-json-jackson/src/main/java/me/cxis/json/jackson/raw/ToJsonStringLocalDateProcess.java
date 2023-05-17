package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import me.cxis.json.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 序列化为字符串时对LocalDate类型的处理，默认不支持LocalDate类型，需要添加对应依赖并设置格式
 */
public class ToJsonStringLocalDateProcess {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("test-username");
        user.setLocalDate(LocalDate.now());

        defaultLocalDate(user);

        customFormatLocalDate(user);
    }

    private static void defaultLocalDate(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalDate.class,
                new LocalDateSerializer(null)
        );
        mapper.registerModule(javaTimeModule);
        System.out.println("jackson默认对LocalDate类型的处理：" + mapper.writeValueAsString(user));
    }

    private static void customFormatLocalDate(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(
                LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
        mapper.registerModule(javaTimeModule);
        System.out.println("jackson自定义LocalDate类型格式：" + mapper.writeValueAsString(user));
    }
}
