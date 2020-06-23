package me.cxis.jvm.bytecode.invoke.virtual;

public class InvokeTest {

    public static void main(String[] args) throws InterruptedException {
        Color yellow = new Yellow();
        Color red = new Red();
        foo(yellow);
        foo(red);
    }

    public static void foo(Color color) {
        color.printColorName();
    }
}
