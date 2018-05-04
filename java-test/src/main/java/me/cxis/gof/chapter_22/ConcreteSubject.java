package me.cxis.gof.chapter_22;

/**
 * Created by cheng.xi on 2017-02-28 14:05.
 * 具体的被观察者
 */
public class ConcreteSubject extends Subject {
    public void doSomething(){
        System.out.println("do something...");
        super.notifyOservers();
    }
}
