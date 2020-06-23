package me.cxis.jvm.bytecode.invoke.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class DynamicTest {

    public void println(String s) {
        System.out.println("my: " + s);
    }

    public static void main(String[] args) throws Throwable {
        DynamicTest dynamicTest = new DynamicTest();

        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(DynamicTest.class, "println", methodType);
        methodHandle.invokeExact(dynamicTest, "xxxxx");
    }
}
