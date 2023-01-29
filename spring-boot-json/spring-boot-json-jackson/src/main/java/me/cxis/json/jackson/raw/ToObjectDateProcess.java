package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.cxis.json.model.User;

/**
 * 字符串反序列化为对象时Date类型的处理，默认可以处理：yyyy-MM-dd、yyyy-MM-ddTHH:mm:ss、毫秒时间戳等
 */
public class ToObjectDateProcess {

    public static void main(String[] args) throws JsonProcessingException {
        yyyyMMdd();

        yyyyMMddTHHmmss();

        timestamp();

        HHmmss();

        yyyyMMddHHmmss();

        yyyyMMddHHmmssSSS();
    }


    private static void timestamp() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"date\":1674979995000}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化Date类型时间戳：" + user);
    }

    private static void yyyyMMdd() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29\"}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化Date类型yyyy-MM-dd：" + user);
    }

    private static void HHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"date\":\"16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化Date类型HH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29 16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化Date类型yyyy-MM-dd HH:mm:ss：" + user);
    }

    private static void yyyyMMddTHHmmss() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29T16:10:24.123\"}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化Date类型yyyy-MM-ddTHH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmssSSS() throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29 16:10:24\"}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson反序列化Date类型yyyy-MM-dd HH:mm:ss.SSS：" + user);
    }
}
