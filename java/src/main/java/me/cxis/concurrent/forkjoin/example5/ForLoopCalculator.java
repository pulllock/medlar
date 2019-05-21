package me.cxis.concurrent.forkjoin.example5;

import java.util.stream.LongStream;

public class ForLoopCalculator implements Calculator {

    @Override
    public long count(long[] numbers) {
        long result = 0;
        for (long number : numbers) {
            result += number;
        }
        return result;
    }

    public static void main(String[] args) {
        ForLoopCalculator calculator = new ForLoopCalculator();
        long[] numbers = LongStream.rangeClosed(1, 1000).toArray();
        long startTime = System.nanoTime();
        long result = calculator.count(numbers);
        long endTime = System.nanoTime();
        System.out.println("Result:" + result + " Coast: " + (endTime - startTime));
    }
}
