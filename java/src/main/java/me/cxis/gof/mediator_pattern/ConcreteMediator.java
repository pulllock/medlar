package me.cxis.gof.mediator_pattern;

public class ConcreteMediator extends Mediator{

    @Override
    public void operation() {
        colleagues.get(0).method1();
    }
}
