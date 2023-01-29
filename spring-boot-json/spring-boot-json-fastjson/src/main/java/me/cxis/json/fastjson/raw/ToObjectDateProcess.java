package me.cxis.json.fastjson.raw;

import com.alibaba.fastjson.JSON;
import me.cxis.json.model.User;

/**
 * 字符串反序列化为对象时Date类型的处理，默认可以处理：yyyy-MM-dd、yyyy-MM-dd HH:mm:ss、yyyy-MM-ddTHH:mm:ss、毫秒时间戳等
 */
public class ToObjectDateProcess {

    public static void main(String[] args) {
        yyyyMMdd();

        yyyyMMddHHmmss();

        yyyyMMddTHHmmss();

        yyyyMMddHHmmssSSS();

        timestamp();

        HHmmss();
    }


    private static void timestamp() {
        String json = "{\"userName\":\"test-username\",\"date\":1674979995000}";
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson反序列化Date类型时间戳：" + user);
    }

    private static void yyyyMMdd() {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29\"}";
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson反序列化Date类型yyyy-MM-dd：" + user);
    }

    private static void HHmmss() {
        String json = "{\"userName\":\"test-username\",\"date\":\"16:10:24\"}";
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson反序列化Date类型HH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmss() {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29 16:10:24.123\"}";
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson反序列化Date类型yyyy-MM-dd HH:mm:ss：" + user);
    }

    private static void yyyyMMddTHHmmss() {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29T16:10:24.123\"}";
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson反序列化Date类型yyyy-MM-ddTHH:mm:ss：" + user);
    }

    private static void yyyyMMddHHmmssSSS() {
        String json = "{\"userName\":\"test-username\",\"date\":\"2023-01-29 16:10:24\"}";
        User user = JSON.parseObject(json, User.class);
        System.out.println("fastjson反序列化Date类型yyyy-MM-dd HH:mm:ss.SSS：" + user);
    }
}
