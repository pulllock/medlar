package me.cxis.thread;

/**
 * Created by cheng.xi on 2017-10-15 12:21.
 */
public class CounterWithSynchronized {
    private int count = 0;
    public int inc() {
        synchronized (this) {
            return ++count;
        }
    }
}
