package me.cxis.spring.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
public class InvokeServiceHandler extends GatewayHandler {

    @Override
    public String process(String request) {
        // invoke service
        String result = "result for request: " + request;

        GatewayHandler next = getNextHandler();
        if (next == null) {
            return result;
        }
        return next.process(request);
    }
}
