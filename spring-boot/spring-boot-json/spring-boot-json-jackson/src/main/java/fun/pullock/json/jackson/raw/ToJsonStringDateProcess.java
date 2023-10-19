package fun.pullock.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.pullock.json.model.User;

import java.util.Date;

/**
 * 序列化为字符串时对Date类型的处理，默认为时间戳
 */
public class ToJsonStringDateProcess {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("test-username");
        user.setDate(new Date());

        defaultDate(user);
    }

    private static void defaultDate(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("jackson默认对Date类型的处理：" + mapper.writeValueAsString(user));
    }
}
