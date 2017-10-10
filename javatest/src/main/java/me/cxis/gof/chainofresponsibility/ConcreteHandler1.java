package me.cxis.gof.chainofresponsibility;

/**
 * Created by cheng.xi on 2017-05-05 17:49.
 */
public class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(String condition) {
        if(condition.equals("1")){
            System.out.println("ConcreteHandler1处理");
            return;
        }else {
            System.out.println("ConcreteHandler1不处理，由其他的Handler处理");
            getSuccessor().handleRequest(condition);
        }
    }
}
