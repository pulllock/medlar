package me.cxis.concurrent.forkjoin.example4;

import java.util.concurrent.RecursiveTask;

public class FibTask extends RecursiveTask<Long> {

    private long fib;

    public FibTask(long fib) {
        this.fib = fib;
    }

    @Override
    protected Long compute() {
        if (fib < 10) {
            return fib(fib);
        } else {
            FibTask left = new FibTask(fib - 1);
            left.fork();
            FibTask right = new FibTask(fib - 2);
            right.fork();
            return left.join() + right.join();
        }
    }

    private Long fib(long n) {
        if (n <= 2) {
            return n;
        } else {
            return fib(n -1) + fib(n -2);
        }
    }
}
