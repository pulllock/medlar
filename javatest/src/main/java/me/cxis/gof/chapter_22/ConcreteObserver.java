package me.cxis.gof.chapter_22;

/**
 * Created by cheng.xi on 2017-02-28 14:07.
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        System.out.println("处理接收到的信息");
    }
}
