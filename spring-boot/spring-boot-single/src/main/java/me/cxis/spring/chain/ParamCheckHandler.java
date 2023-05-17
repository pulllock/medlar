package me.cxis.spring.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class ParamCheckHandler extends GatewayHandler {

    @Override
    public String process(String request) {
        if (request == null || request.contains("error")) {
            return "param error!";
        }

        GatewayHandler next = getNextHandler();
        if (next == null) {
            return null;
        }
        return next.process(request);
    }
}
