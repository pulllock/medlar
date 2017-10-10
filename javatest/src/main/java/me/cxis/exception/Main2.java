package me.cxis.exception;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by cheng.xi on 2017-03-15 09:40.
 */
public class Main2 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future future = pool.submit(new LogThread2());
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            pool.submit(new LogThread2());
        }
    }
}
