package me.cxis.gof.chainofresponsibility;

/**
 * Created by cheng.xi on 2017-05-05 17:49.
 */
public class ConcreteHandlerX extends Handler {
    @Override
    public void handleRequest(String condition) {
        //一般是最后一个处理者
        System.out.println("ConcreteHandlerX处理");
    }
}
