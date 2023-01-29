package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.cxis.json.model.User;

/**
 * 序列化为字符串时处理null值，默认保留null值
 */
public class ToJsonStringNullValueProcess {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("test-username");

        defaultKeepNull(user);

        filterNull(user);
    }

    private static void filterNull(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println("jackson通过设置Include.NON_NULL可过滤null值：" + mapper.writeValueAsString(user));
    }

    private static void defaultKeepNull(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("jackson默认保留null值：" + mapper.writeValueAsString(user));
    }
}
