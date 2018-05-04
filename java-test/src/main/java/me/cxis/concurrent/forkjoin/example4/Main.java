package me.cxis.concurrent.forkjoin.example4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Long> future = pool.submit(new FibTask(45));
        long result = future.get();
        System.out.println(result);
        pool.shutdown();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
