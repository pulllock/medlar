package fun.pullock.json.jackson.raw;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.pullock.json.model.User;

/**
 * 字符串反序列化为对象时对没有引号的字段的处理，jackson默认不支持字段名没有引号
 */
public class ToObjectUnquotedFieldNameProcess {

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{userName:\"test-username\"}";

        supportUnquotedFieldName(json);

        defaultUnquotedFieldName(json);
    }

    private static void supportUnquotedFieldName(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson可通过设置JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES为true来支持字段名没有引号：" + user);
    }


    private static void defaultUnquotedFieldName(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println("jackson默认不支持字段名没有引号：" + user);
    }
}
