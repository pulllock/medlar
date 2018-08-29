package me.cxis.gof.chainofresponsibility.example1;

public abstract class Handler {

    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    protected abstract void handleRequest(String request);
}
