package me.cxis.jvm.bytecode;

public class InitTest {

    private int a = 1;

    {
        int b = 2;
    }

    public InitTest() {
        int c = 3;
    }
}
