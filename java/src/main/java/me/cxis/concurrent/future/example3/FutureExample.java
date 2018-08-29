package me.cxis.concurrent.future.example3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<String> totalList = new ArrayList<>();
        List<Future<List<String>>> list = new ArrayList<>();

        int pageIndex;
        int maxPage = 6;

        for (pageIndex = 0; pageIndex < maxPage; pageIndex++) {
            Future<List<String>> future = executor.submit(new QueryThread(pageIndex));
            list.add(future);
        }

        for (Future<List<String>> future : list) {
            totalList.addAll(future.get());
        }

        for (int i = 0; i < totalList.size(); i++) {
            System.out.println(totalList.get(i));
        }

        executor.shutdown();
    }
}
