package me.cxis.concurrent.countdownlatch.example1;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker {

    public DatabaseHealthChecker(CountDownLatch countDownLatch) {
        super(countDownLatch, "Database Service");
    }

    @Override
    protected void verifyService() {
        long time = 5000;
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is up, costs " + (time / 1000) + "seconds");
    }
}
