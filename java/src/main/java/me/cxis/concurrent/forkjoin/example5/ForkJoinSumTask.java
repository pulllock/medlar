package me.cxis.concurrent.forkjoin.example5;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumTask extends RecursiveTask<Long> {

    private long[] numbers;

    private int from;

    private int to;

    public ForkJoinSumTask(long[] numbers, int from, int to) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        if (to - from < 10) {
            long result = 0;
            for (int i = from; i <= to; i++) {
                result += numbers[i];
            }
            return result;
        } else {
            int middle = (from + to) / 2;
            ForkJoinSumTask left = new ForkJoinSumTask(numbers, from, middle);
            ForkJoinSumTask right = new ForkJoinSumTask(numbers, middle + 1, to);
            left.fork();
            right.fork();

            return left.join() + right.join();
        }
    }
}
