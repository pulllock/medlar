package fun.pullock.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import fun.pullock.json.model.User;

import java.time.LocalDate;

/**
 * 序列化为字符串时对LocalDate类型的处理，默认为yyyy-MM-dd格式
 */
public class ToJsonStringLocalDateProcess {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("test-username");
        user.setLocalDate(LocalDate.now());

        defaultLocalDate(user);
    }

    private static void defaultLocalDate(User user) {
        System.out.println("fastjson默认对LocalDate类型的处理：" + JSON.toJSONString(user));
    }
}
