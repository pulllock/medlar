package me.cxis.gof.observer_pattern.example2;

public class Client {

    public static void main(String[] args) {
        ConcreteSubject concreteSubject = new ConcreteSubject();
        Observer concreteObserver = new ConcreteObserver();
        concreteSubject.addObserver(concreteObserver);;

        concreteSubject.update();
    }
}
