package me.cxis.spring.chain;

public abstract class GatewayHandler {

    protected GatewayHandler nextHandler;

    public abstract String process(String request);

    public GatewayHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(GatewayHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
