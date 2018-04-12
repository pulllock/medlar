package me.cxis.concurrent.future.futuretask.example1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> futureTask = new FutureTask<>(() -> "ok");

        executor.execute(futureTask);

        System.out.println(futureTask.get());

        executor.shutdown();
    }
}
