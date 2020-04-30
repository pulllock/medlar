package me.cxis.gof.observer_pattern.example2;

public class ConcreteSubject extends Subject {

    public void update() {
        this.notifyObservers();
    }
}
