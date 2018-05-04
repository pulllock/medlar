package me.cxis.thread;

/**
 * Created by cheng.xi on 2017-10-15 14:48.
 */
public class Semaphore {
    private boolean signal = false;

    public synchronized void take() {
        this.signal = true;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while(!this.signal) {
            wait();
        }
        this.signal = false;
    }
}
