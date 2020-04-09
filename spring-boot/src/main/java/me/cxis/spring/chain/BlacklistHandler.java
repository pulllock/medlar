package me.cxis.spring.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class BlacklistHandler extends GatewayHandler {

    @Override
    public String process(String request) {
        if (request.contains("hack")) {
            return "ip locked";
        }

        GatewayHandler next = getNextHandler();
        if (next == null) {
            return null;
        }
        return next.process(request);
    }
}
