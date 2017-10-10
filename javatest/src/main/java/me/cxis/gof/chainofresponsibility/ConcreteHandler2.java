package me.cxis.gof.chainofresponsibility;

/**
 * Created by cheng.xi on 2017-05-05 17:49.
 */
public class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(String condition) {
        if(condition.equals("2")){
            System.out.println("ConcreteHandler2处理");
            return;
        }else {
            System.out.println("ConcreteHandler2不处理，由其他的Handler处理");
            getSuccessor().handleRequest(condition);
        }
    }
}
