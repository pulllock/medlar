package me.cxis.spring.service.impl;

import me.cxis.spring.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void testAsync() {
        try {
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public String testAsync1() {
        Future<String> future = doSomething();
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "xxx";
    }

    public Future<String> doSomething() {
        try {
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new AsyncResult<>("xxxxxxx");
    }
}
