package me.cxis.gof.chain_of_responsibility_pattern.example1;

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
