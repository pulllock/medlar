package me.cxis.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by cheng.xi on 30/11/2016.
 */
public class CallableAndFutureTest {
    public static void main(String[] args) {
        Callable<Integer> callable = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask).start();

        try {
            Thread.sleep(5000);
            System.out.println(futureTask.get());
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
