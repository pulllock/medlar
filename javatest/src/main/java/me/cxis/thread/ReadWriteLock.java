package me.cxis.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng.xi on 2017-10-15 12:34.
 */
public class ReadWriteLock {
    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || writeRequests >0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequests--;
        writers++;

    }

    public synchronized void unlockWrite() {
        writers--;
        notifyAll();
    }

    public class ReentrantReadWriteLock {
        private Map<Thread, Integer> readingThreads = new HashMap<>();
        private int writers = 0;
        private int writeRequests = 0;

        public synchronized void lockRead() throws InterruptedException {
            Thread callingThread = Thread.currentThread();
            while (!canGrantAccess(callingThread)) {
                wait();
            }
            readingThreads.put(callingThread, getAccessCount(callingThread) + 1);
        }

        public synchronized void unlockRead() {
            Thread callingThread = Thread.currentThread();
            int accessCount = getAccessCount(callingThread);
            if (accessCount == 1) {
                readingThreads.remove(callingThread);
            } else {
                readingThreads.put(callingThread, (accessCount - 1));
            }
            notifyAll();
        }
        private Integer getAccessCount(Thread callingThread) {
            Integer accessCount = readingThreads.get(callingThread);
            if (accessCount == null) return 0;
            return accessCount.intValue();
        }

        private boolean canGrantAccess(Thread callingThread) {
            if (writers > 0) return false;
            if (isReader(callingThread)) return true;
            if (writeRequests > 0) return false;
            return true;
        }

        private boolean isReader(Thread callingThread) {
            return readingThreads.get(callingThread) != null;
        }
    }
}
