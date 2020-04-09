package me.cxis.gof.chain_of_responsibility_pattern.example3;

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
