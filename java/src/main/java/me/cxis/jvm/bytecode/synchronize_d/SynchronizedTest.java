package me.cxis.jvm.bytecode.synchronize_d;

public class SynchronizedTest {

    private int count = 0;

    public void increase() {
        ++count;
    }

    public synchronized void increase1() {
        ++count;
    }

    public void increase2() {
        synchronized (this) {
            ++count;
        }
    }

    public int getCount() {
        return count;
    }
}
