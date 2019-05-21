package me.cxis.concurrent.forkjoin.example5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

public class ExecutorServiceCalculator implements Calculator {

    private int threadNum;

    private ExecutorService pool;

    public ExecutorServiceCalculator() {
        threadNum = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(threadNum);
    }

    @Override
    public long count(long[] numbers) {
        List<Future<Long>> futures = new ArrayList<>();

        int part = numbers.length / threadNum;

        for (int i = 0; i < threadNum; i++) {
            int from = i * part;
            int to = (i == threadNum - 1) ? numbers.length - 1 : (i + 1) * part - 1;
            futures.add(pool.submit(new ExecutorServiceSumTask(numbers, from, to)));
        }

        long result = 0;

        for (Future<Long> future : futures) {
            try {
                result += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();

        return result;
    }

    public static void main(String[] args) {
        ExecutorServiceCalculator calculator = new ExecutorServiceCalculator();
        long[] numbers = LongStream.rangeClosed(1, 1000).toArray();
        long startTime = System.nanoTime();
        long result = calculator.count(numbers);
        long endTime = System.nanoTime();
        System.out.println("Result:" + result + " Coast: " + (endTime - startTime));
    }
}
