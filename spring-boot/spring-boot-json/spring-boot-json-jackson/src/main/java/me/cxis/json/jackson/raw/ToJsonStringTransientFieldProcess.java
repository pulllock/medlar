package me.cxis.json.jackson.raw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.cxis.json.model.User;

/**
 * 序列化为字符串时对transient属性的处理，jackson默认会序列化transient属性
 */
public class ToJsonStringTransientFieldProcess {

    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUserName("test-username");
        user.setTransientField("transient-value");

        defaultSupportTransientField(user);

        setIgnoreTransientField(user);
    }

    private static void setIgnoreTransientField(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        System.out.println("jackson可通过设置MapperFeature.PROPAGATE_TRANSIENT_MARKER为true来忽略transient属性：" + mapper.writeValueAsString(user));
    }

    private static void defaultSupportTransientField(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("jackson默认会序列化transient属性：" + mapper.writeValueAsString(user));
    }
}
