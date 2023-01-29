package me.cxis.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import me.cxis.json.model.User;

import java.time.LocalTime;

/**
 * 序列化为字符串时对LocalTime类型的处理，默认为HH:mm:ss.SSS格式
 */
public class ToJsonStringLocalTimeProcess {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test-username");
        user.setLocalTime(LocalTime.now());

        defaultLocalTime(user);
    }

    private static void defaultLocalTime(User user) {
        System.out.println("fastjson默认对LocalTime类型的处理：" + JSON.toJSONString(user));
    }
}
