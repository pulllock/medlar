package me.cxis.gof.observer_pattern;

public class ConcreteSubject extends Subject{

    @Override
    public void notifyy() {
        observers.forEach(
                Observer::update
        );
    }
}
