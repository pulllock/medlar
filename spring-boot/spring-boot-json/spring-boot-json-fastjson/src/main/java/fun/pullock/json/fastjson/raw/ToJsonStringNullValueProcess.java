package fun.pullock.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import fun.pullock.json.model.User;

/**
 * 序列化为字符串时处理null值，默认过滤null值
 */
public class ToJsonStringNullValueProcess {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test-username");

        defaultFilterNull(user);

        keepNull(user);
    }

    private static void keepNull(User user) {
        System.out.println("fastjson通过设置SerializerFeature.WriteMapNullValue保留null值："
                + JSON.toJSONString(user, SerializerFeature.WriteMapNullValue));
    }

    private static void defaultFilterNull(User user) {
        System.out.println("fastjson默认过滤null值：" + JSON.toJSONString(user));
    }
}
