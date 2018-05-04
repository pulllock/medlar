package me.cxis.concurrent.countdownlatch.example1;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {

    public CacheHealthChecker(CountDownLatch countDownLatch) {
        super(countDownLatch, "Cache Service");
    }

    @Override
    protected void verifyService() {
        long time = 10000;
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is up, costs " + (time / 1000) + "seconds");
    }
}
