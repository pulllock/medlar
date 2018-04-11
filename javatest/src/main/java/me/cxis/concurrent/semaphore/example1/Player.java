package me.cxis.concurrent.semaphore.example1;

import java.util.concurrent.Semaphore;

public class Player implements Runnable {

    private Semaphore semaphore;

    private int id;

    public Player(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(id + "获取到一个许可，还剩下： " + semaphore.availablePermits() + "个许可");
            Thread.sleep(1000);
            semaphore.release();
            System.out.println(id + "释放一个许可，还剩下： " + semaphore.availablePermits() + "个许可");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
