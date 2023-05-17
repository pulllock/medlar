package me.cxis.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import me.cxis.json.model.User;

import java.util.Date;

/**
 * 序列化为字符串时对Date类型的处理，默认为时间戳
 */
public class ToJsonStringDateProcess {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test-username");
        user.setDate(new Date());

        defaultDate(user);
    }

    private static void defaultDate(User user) {
        System.out.println("fastjson默认对Date类型的处理：" + JSON.toJSONString(user));
    }
}
