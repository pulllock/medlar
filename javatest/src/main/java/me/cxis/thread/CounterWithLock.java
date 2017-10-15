package me.cxis.thread;

/**
 * Created by cheng.xi on 2017-10-15 12:22.
 */
public class CounterWithLock {
    public class Lock{
        private boolean isLocked = false;

        public synchronized void lock() throws InterruptedException {
            while (isLocked) {
                wait();
            }
            isLocked = true;
        }

        public synchronized void unlock() {
            isLocked = false;
            notify();
        }
    }

    private Lock lock = new Lock();
    private int count = 0;

    public int inc() throws InterruptedException {
        lock.lock();
        int newCount = ++count;
        lock.unlock();
        return newCount;
    }


    public class ReentrantLock {
        boolean isLocked = false;
        Thread lockedBy = null;
        int lockedCount = 0;

        public synchronized void lock() throws InterruptedException {
            Thread callingThread = Thread.currentThread();
            while (isLocked && lockedBy != callingThread) {
                wait();
            }
            isLocked = true;
            lockedCount++;
            lockedBy = callingThread;
        }

        public synchronized void unlock() {
            if (Thread.currentThread() == this.lockedBy) {
                lockedCount--;
                if (lockedCount == 0) {
                    isLocked = false;
                    notify();
                }
            }
        }
    }
}
