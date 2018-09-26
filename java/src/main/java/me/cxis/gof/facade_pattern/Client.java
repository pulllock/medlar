package me.cxis.gof.facade_pattern;

public class Client {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }
}
