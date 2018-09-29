package me.cxis.gof.chain_of_responsibility_pattern;

public class ConcreteHandler extends Handler {

    @Override
    public void handleRequest(String request) {
        if (request.equals(true)) {
            // ...
        } else {
            this.successor.handleRequest(request);
        }
    }
}
