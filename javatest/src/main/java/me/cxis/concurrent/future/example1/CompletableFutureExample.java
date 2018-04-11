package me.cxis.concurrent.future.example1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
            () -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1;
            },
            executor
        );

        System.out.println(future.isDone());
        CompletableFuture<Integer> future1 = future.thenApply((x) -> x);
        System.out.println(future1.isDone());
        System.out.println(future1.get());

        executor.shutdown();
    }
}
