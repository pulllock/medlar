package me.cxis.jvm.bytecode.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

    private static int count = 0;

    public static void foo() {
        new Exception("test#" + (count++)).printStackTrace();
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = Class.forName("me.cxis.jvm.bytecode.reflection.ReflectionTest");
        Method method = clazz.getMethod("foo");
        for (int i = 0; i < 20; i++) {
            method.invoke(null);
        }
    }
}
