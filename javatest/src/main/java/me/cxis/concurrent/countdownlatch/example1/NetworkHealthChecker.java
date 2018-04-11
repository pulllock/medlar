package me.cxis.concurrent.countdownlatch.example1;

import java.util.concurrent.CountDownLatch;

public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker(CountDownLatch countDownLatch) {
        super(countDownLatch, "Network Service");
    }

    @Override
    protected void verifyService() {
        long time = 7000;
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is up, costs " + (time / 1000) + "seconds");
    }
}
