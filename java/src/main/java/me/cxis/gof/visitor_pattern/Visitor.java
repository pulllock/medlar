package me.cxis.gof.visitor_pattern;

public abstract class Visitor {

    public abstract void visit(ConcreteElementA concreteElementA);

    public abstract void visit(ConcreteElementB concreteElementB);
}
