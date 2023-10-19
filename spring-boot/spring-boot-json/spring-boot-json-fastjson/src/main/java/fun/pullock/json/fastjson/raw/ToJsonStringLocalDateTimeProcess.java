package fun.pullock.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import fun.pullock.json.model.User;

import java.time.LocalDateTime;

/**
 * 序列化为字符串时对LocalDateTime类型的处理，默认为yyyy-MM-ddTHH:mm:ss.SSS格式
 */
public class ToJsonStringLocalDateTimeProcess {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test-username");
        user.setLocalDateTime(LocalDateTime.now());

        defaultLocalDateTime(user);
    }

    private static void defaultLocalDateTime(User user) {
        System.out.println("fastjson默认对LocalDateTime类型的处理：" + JSON.toJSONString(user));
    }
}
