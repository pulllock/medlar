package me.cxis.gof.chainofresponsibility.example1;

public class ConcreteHandler extends Handler {
    @Override
    protected void handleRequest(String request) {
        if (request.startsWith("xxx")) {

        } else {
            this.successor.handleRequest(request);
        }
    }
}
