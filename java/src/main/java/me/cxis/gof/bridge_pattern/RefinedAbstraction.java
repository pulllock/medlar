package me.cxis.gof.bridge_pattern;

public class RefinedAbstraction extends Abstraction {

    @Override
    public void operation() {
        implementor.operationImpl();
    }
}
