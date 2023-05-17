package me.cxis.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import me.cxis.json.model.User;

/**
 * 字符串反序列化为对象时对没有引号的字段的处理，fastjson默认支持字段名没有引号
 */
public class ToObjectUnquotedFieldNameProcess {

    public static void main(String[] args) {
        String json = "{userName:\"test-username\",\"unknownProperty\":\"test-value\"}";

        defaultUnquotedFieldName(json);
    }

    private static void defaultUnquotedFieldName(String json) {
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson默认支持字段名没有引号：" + user);
    }
}
