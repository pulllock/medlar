package me.cxis.concurrent.future.example4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Map<String, String>> future = executor.submit(() -> {
            Thread.sleep(2000);
            return new HashMap<>();
        });

        Thread.sleep(1000);

        boolean flag = true;
        while (flag) {
            if (future.isDone() && !future.isCancelled()) {
                future.get();
                flag = false;
            }
        }

        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
