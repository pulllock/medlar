package me.cxis.gof.observer_pattern.example3;

public class Client {

    public static void main(String[] args) {
        ConcreteEventSource concreteEventSource = new ConcreteEventSource();
        EventListener eventListener = new ConcreteEventListener();
        concreteEventSource.addEventListener(eventListener);

        concreteEventSource.eventHappened();
    }
}
