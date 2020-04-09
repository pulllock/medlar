package me.cxis.gof.chain_of_responsibility_pattern.example1;

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
