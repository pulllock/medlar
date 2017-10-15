package me.cxis.thread;

/**
 * Created by cheng.xi on 2017-10-15 22:14.
 */
public class CountingSemaphore {
    private int signals = 0;

    public synchronized void take() {
        this.signals++;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while (this.signals == 0) {
            wait();
        }
        this.signals--;
    }
}
