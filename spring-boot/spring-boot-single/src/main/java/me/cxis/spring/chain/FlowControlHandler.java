package me.cxis.spring.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class FlowControlHandler extends GatewayHandler {

    @Override
    public String process(String request) {
        GatewayHandler next = getNextHandler();
        if (next == null) {
            return null;
        }
        return next.process(request);
    }
}
