package me.cxis.concurrent.countdownlatch.example1;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable {

    private CountDownLatch countDownLatch;

    private String serviceName;

    private boolean serviceUp;

    public BaseHealthChecker(CountDownLatch countDownLatch, String serviceName) {
        this.countDownLatch = countDownLatch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Exception e) {
            serviceUp = false;
            e.printStackTrace();
        } finally {
            if (countDownLatch != null)
                countDownLatch.countDown();
        }
    }

    protected abstract void verifyService();

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    public void setServiceUp(boolean serviceUp) {
        this.serviceUp = serviceUp;
    }
}
