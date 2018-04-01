package me.cxis.lambda;

import java.io.File;
import java.io.FileFilter;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntBinaryOperator;

import static java.security.AccessController.doPrivileged;

public class LambdaTest2 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxxx");
            }
        });

        new Thread(() -> System.out.println("xxx"));

        List<String> list = new ArrayList<>();

        list.forEach(s -> System.out.println(s));

        list.forEach(System.out::println);

        IntBinaryOperator intBinaryOperator = (int x, int y) -> x + y;
        System.out.println(intBinaryOperator.applyAsInt(1, 2));

        DoubleSupplier doubleSupplier = () -> 42;
        System.out.println(doubleSupplier.getAsDouble());

        Consumer<String> stringConsumer = (String s) -> System.out.println(s);
        stringConsumer.accept("x");

        FileFilter java = (File f) -> f.getName().endsWith("*.java");

        String user = doPrivileged((PrivilegedAction<String>) () -> System.getProperty("user.name"));
        System.out.println(user);

        Comparator<String> c = (s1, s2) -> s1.compareToIgnoreCase(s2);

        FileFilter java1 = f -> f.getName().endsWith(".java");
    }
}
