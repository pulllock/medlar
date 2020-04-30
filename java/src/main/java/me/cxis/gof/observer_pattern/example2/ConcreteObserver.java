package me.cxis.gof.observer_pattern.example2;

public class ConcreteObserver implements Observer {

    @Override
    public void receiveUpdateFromSubject() {
        System.out.println("receive update form subject......");
    }
}
