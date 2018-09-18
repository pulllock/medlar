package me.cxis.gof.composite_pattern;

public abstract class Component {

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract Component getChild(int i);

    public abstract void operation();
}
