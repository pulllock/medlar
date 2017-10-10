package me.cxis.gof.chapter_22;

/**
 * Created by cheng.xi on 2017-02-28 14:08.
 */
public class Client {
    public static void main(String[] args) {
        //创建一个被观察者
        ConcreteSubject subject = new ConcreteSubject();
        //定义一个观察者
        Observer observer = new ConcreteObserver();

        //观察者开始观察被观察者
        subject.addObserver(observer);

        //观察者活动
        subject.doSomething();
    }
}
