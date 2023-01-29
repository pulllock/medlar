package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.cxis.json.model.User;

/**
 * 字符串反序列化为对象时处理未知属性，jackson默认有未知属性时会报错
 */
public class ToObjectUnknownPropertyProcess {

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"userName\":\"test-username\",\"unknownProperty\":\"test-value\"}";

        setIgnoreUnknownProperty(json);

        defaultUnknownPropertyError(json);
    }

    private static void setIgnoreUnknownProperty(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson可通过DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES为false来设置忽略未知属性：" + user);
    }

    private static void defaultUnknownPropertyError(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson默认有未知属性时会报错：" + user);
    }
}
