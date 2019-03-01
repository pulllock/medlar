package me.cxis.gof.observer_pattern.example1;

public class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println(this.getClass().getSimpleName() + ":subject's state changed");
    }
}
