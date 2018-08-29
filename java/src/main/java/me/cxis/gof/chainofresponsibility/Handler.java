package me.cxis.gof.chainofresponsibility;

/**
 * Created by cheng.xi on 2017-05-05 17:46.
 */
public abstract class Handler {
    protected Handler successor;

    public abstract void handleRequest(String condition);

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
