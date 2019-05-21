package me.cxis.concurrent.forkjoin.example5;

import java.util.concurrent.Callable;

public class ExecutorServiceSumTask implements Callable<Long> {

    private long[] numbers;

    private int from;

    private int to;

    public ExecutorServiceSumTask(long[] numbers, int from, int to) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        long result = 0;
        for (int i = from; i <= to; i++) {
            result += numbers[i];
        }
        return result;
    }
}
