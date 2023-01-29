package me.cxis.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import me.cxis.json.model.User;

/**
 * 序列化为字符串时对transient属性的处理，fastjson默认会忽略transient属性
 */
public class ToJsonStringTransientFieldProcess {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test-username");
        user.setTransientField("transient-value");

        defaultIgnoreTransientField(user);

    }

    private static void defaultIgnoreTransientField(User user) {
        System.out.println("fastjson默认会忽略transient属性：" + JSON.toJSONString(user));
    }
}
