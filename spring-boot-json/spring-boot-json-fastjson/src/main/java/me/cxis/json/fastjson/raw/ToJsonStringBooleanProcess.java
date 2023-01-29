package me.cxis.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import me.cxis.json.model.User;

/**
 * 序列化为字符串时对boolean类型字段的处理，fastjson会将boolean类型字段的开头的is去掉
 */
public class ToJsonStringBooleanProcess {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test-username");
        user.setAdmin(true);

        defaultBoolean(user);
    }

    private static void defaultBoolean(User user) {
        System.out.println("fastjson会将boolean类型字段的开头的is去掉：" + JSON.toJSONString(user));
    }
}
