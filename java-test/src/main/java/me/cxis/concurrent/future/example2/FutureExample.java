package me.cxis.concurrent.future.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Future<String>> futures = new ArrayList<>();

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            Future<String> future = executor.submit(() -> "执行结果：任务id=" + taskId);
            futures.add(future);
        }

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown();
    }

}
