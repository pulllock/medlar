package me.cxis.gof.builder_pattern;

public class ConcreteBuilder extends Builder {

    @Override
    public void buildPartA() {
        product.setPartA("xxxx");
    }

    @Override
    public void buildPartB() {

    }

    @Override
    public void buildPartC() {

    }
}
