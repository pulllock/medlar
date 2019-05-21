package me.cxis.concurrent.forkjoin.example5;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ForkJoinCalculator implements Calculator {
    private ForkJoinPool pool;


    public ForkJoinCalculator() {
        pool = new ForkJoinPool();
    }

    @Override
    public long count(long[] numbers) {
        return pool.invoke(new ForkJoinSumTask(numbers, 0, numbers.length - 1));
    }

    public static void main(String[] args) {
        ForkJoinCalculator calculator = new ForkJoinCalculator();
        long[] numbers = LongStream.rangeClosed(1, 1000).toArray();
        long startTime = System.nanoTime();
        long result = calculator.count(numbers);
        long endTime = System.nanoTime();
        System.out.println("Result:" + result + " Coast: " + (endTime - startTime));
    }
}
