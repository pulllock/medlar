package fun.pullock.cloud.gateway.server.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义全局异常处理
 */
public class GatewayErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> rawAttributes = super.getErrorAttributes(request, options);

        Map<String, Object> newAttributes = new LinkedHashMap<>();
        newAttributes.put("code", rawAttributes.get("status"));
        newAttributes.put("message", rawAttributes.get("message"));
        newAttributes.put("success", false);
        newAttributes.put("data", null);
        newAttributes.put("status", HttpStatus.OK.value());
        return newAttributes;
    }
}
