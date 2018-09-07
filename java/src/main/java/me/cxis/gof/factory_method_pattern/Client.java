package me.cxis.gof.factory_method_pattern;

public class Client {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory(); // 可以使用配置文件实现这里
        Product product = factory.factoryMethod();
        product.methodDiff();
        product.methodSame();
    }
}
