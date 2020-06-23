package me.cxis.jvm.bytecode.lambda;

public class LambdaTest {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("xxxx");
        };

        System.out.println(runnable.getClass().getName());
        runnable.run();
    }
}
