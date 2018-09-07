package me.cxis.gof.simple_factory_pattern;

public class Client {

    public static void main(String[] args) {
        Product product = Factory.getProduct("A");

        product.methodDiff();
        product.methodSame();
    }
}
