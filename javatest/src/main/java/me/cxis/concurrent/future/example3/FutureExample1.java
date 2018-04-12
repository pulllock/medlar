package me.cxis.concurrent.future.example3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureExample1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<String> totalList = new ArrayList<>();

        CompletionService<List<String>> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < 6; i++) {
            completionService.submit(new QueryThread(i));
        }

        for (int i = 0; i < 6; i++) {
            totalList.addAll(completionService.take().get());
        }

        for (int i = 0; i < totalList.size(); i++) {
            System.out.println(totalList.get(i));
        }

        executor.shutdown();
    }
}
