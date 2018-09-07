package me.cxis.gof.simple_factory_pattern;

public class Factory {

    public static Product getProduct(String type) {
        Product product = null;
        if (type.equals("A")) {
            product = new ConcreteProductA();
        } else if (type.equals("B")) {
            product = new ConcreteProductB();
        }

        return product;
    }
}
