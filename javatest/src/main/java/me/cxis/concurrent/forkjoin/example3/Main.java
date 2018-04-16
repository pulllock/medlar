package me.cxis.concurrent.forkjoin.example3;

import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountingTask task = new CountingTask(Paths.get("D:/"));

        Integer count;

        Future<Integer> future = forkJoinPool.submit(task);
        count = future.get();
        System.out.println(count);
        forkJoinPool.shutdown();
    }
}
