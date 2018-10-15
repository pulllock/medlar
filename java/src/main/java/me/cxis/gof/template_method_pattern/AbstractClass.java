package me.cxis.gof.template_method_pattern;

public abstract class AbstractClass {

    public void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    protected abstract void primitiveOperation3();

    protected abstract void primitiveOperation2();

    protected abstract void primitiveOperation1();

}
