package me.cxis.gof.prototype_pattern;

public class ConcretePrototype implements Prototype {

    private String attr;

    @Override
    public Prototype clone() {
        Prototype prototype = new ConcretePrototype();
        ((ConcretePrototype) prototype).setAttr(this.attr);
        return prototype;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }
}
