package me.cxis.gof.observer_pattern.example1;

public class Client {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();

        Observer observer = new ConcreteObserver();

        subject.attach(observer);

        ((ConcreteSubject) subject).changeStatus(0);

        System.out.println("======");

        ((ConcreteSubject) subject).changeStatus(1);
    }
}
