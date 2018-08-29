package me.cxis.gof.chainofresponsibility;

/**
 * Created by cheng.xi on 2017-05-05 17:53.
 */
public class Client {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handlerX = new ConcreteHandlerX();

        handler1.setSuccessor(handler2);
        handler2.setSuccessor(handlerX);

        handler1.handleRequest("2");
    }
}
