package me.cxis.concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(5000);
            return "ok";
        });

        new Thread(futureTask).start();
        System.out.println("do something");
        String result = futureTask.get();
        System.out.println(result);
    }
}
