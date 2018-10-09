package me.cxis.gof.mediator_pattern;

public abstract class Colleague {

    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void method1();

    public void method2() {
        mediator.operation();
    }
}
