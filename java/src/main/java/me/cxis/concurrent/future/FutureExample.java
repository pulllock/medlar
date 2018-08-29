package me.cxis.concurrent.future;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        System.out.println("do something");
        Thread.sleep(1000);
        String result = future.get();
        System.out.println(result);
        executorService.shutdown();
    }



    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "ok";
        }
    }
}
