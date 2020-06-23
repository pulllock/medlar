package me.cxis.jvm.bytecode.lambda.inner;

public class InnerTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("xxxxx");
            }
        };
        runnable.run();
    }
}
