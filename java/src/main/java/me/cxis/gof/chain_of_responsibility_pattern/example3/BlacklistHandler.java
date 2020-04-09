package me.cxis.gof.chain_of_responsibility_pattern.example3;

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
