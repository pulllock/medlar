package me.cxis.gof.chain_of_responsibility_pattern.example3;

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
