package me.cxis.lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by cheng.xi on 2017-06-24 22:12.
 */
public class LambdaTest {

    public static void main(String args[]){
        //before java 8
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java8 new Thread...");
            }
        }).start();

        // java8 lambda
        new Thread(() -> System.out.println("java 8 lambda thread...")).start();

        JButton button = new JButton("show");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("before action listener..");
            }
        });

        button.addActionListener((e) -> System.out.println("java 8 action listener..."));

        List<String> lists = Arrays.asList("la","sd","sddd","jxxx");
        System.out.println("before java 8 forEach");
        for(String s : lists){
            System.out.println(s);
        }

        System.out.println("java 8 forEach");
        lists.forEach(s -> System.out.println(s));

        System.out.println("annother java 8 forEach");
        lists.forEach(System.out::println);

        System.out.println("java 8 filter...");
        lists.stream()
                .filter((str) -> str.startsWith("s"))
                .forEach(s -> System.out.println(s));
        System.out.println("java 8 filter...");
        lists.stream()
                .filter((str) -> str.length() >= 4)
                .forEach(s -> System.out.println(s));

        System.out.println("java 8 predicate..");
        Predicate<String> startWithS = (n) -> n.startsWith("s");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        lists.stream()
                .filter(startWithS.and(fourLetterLong))
                .forEach((n) -> System.out.println(n));

        System.out.println("java 8 map reduce");
        List<Integer> origin =  Arrays.asList(10,1000,200,30,40,40);
        for (Integer cost : origin){
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        System.out.println("using java 8");
        origin.stream()
                .map((cost) -> cost + .12 * cost)
                .forEach(System.out::println);

        System.out.println("java 8 filter");
        List<String> filtered = lists.stream()
                .filter(x -> x.length() > 2)
                .collect(Collectors.toList());
        System.out.println(filtered);

        System.out.println("java 8 join");
        String joined = lists.stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.joining("|"));
        System.out.println(joined);

        System.out.println("java 8 distinct");
        origin.stream()
                .map(i -> i * i)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("java 8 max min sum avg");
        List<Integer> scoes = Arrays.asList(10,34,56,75,43,34,55);
        IntSummaryStatistics statistics = scoes.stream()
                .mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println(statistics.getCount());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());

    }


}
