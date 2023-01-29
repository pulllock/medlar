package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.cxis.json.model.User;

/**
 * 序列化为字符串时对boolean类型字段的处理，jackson会将boolean类型字段的开头的is去掉
 */
public class ToJsonStringBooleanProcess {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("test-username");

        defaultBoolean(user);
    }


    private static void defaultBoolean(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("jackson会将boolean类型字段的开头的is去掉：" + mapper.writeValueAsString(user));
    }


}
