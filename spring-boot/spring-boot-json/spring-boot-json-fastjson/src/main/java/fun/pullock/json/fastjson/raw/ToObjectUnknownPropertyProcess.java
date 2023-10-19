package fun.pullock.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import fun.pullock.json.model.User;

/**
 * 字符串反序列化为对象时处理未知属性，默认会忽略未知属性
 */
public class ToObjectUnknownPropertyProcess {

    public static void main(String[] args) {
        String json = "{\"userName\":\"test-username\",\"unknownProperty\":\"test-value\"}";

        defaultIgnoreUnknownProperty(json);

        setUnknownPropertyError(json);
    }

    private static void setUnknownPropertyError(String json) {
        int featureValue = JSON.DEFAULT_PARSER_FEATURE & ~Feature.IgnoreNotMatch.getMask();
        User user = JSON.parseObject(json, User.class, featureValue);
        System.out.println("fastjson可通过设置Feature.IgnoreNotMatch来启用遇到未知属性报错：" + user);
    }

    private static void defaultIgnoreUnknownProperty(String json) {
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson默认会忽略掉未知属性：" + user);
    }
}
