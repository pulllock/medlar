package me.cxis.concurrent.semaphore.example1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);

        System.out.println("初始化，可用许可数：" + semaphore.availablePermits());

        for (int i = 0; i < 10; i++) {
            executor.execute(new Player(semaphore, i + 1));
        }

        executor.shutdown();
    }
}
