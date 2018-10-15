package me.cxis.gof.visitor_pattern;

public class ConcreteElementA implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operation() {

    }
}
