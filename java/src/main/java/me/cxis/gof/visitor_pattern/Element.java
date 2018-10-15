package me.cxis.gof.visitor_pattern;

public interface Element {

    void accept(Visitor visitor);
}
