package me.cxis.queue.blockingqueue.self;

public class MyArrayBlockingQueue<T> {

    private Object[] items = null;

    private int count;

    private Object full = new Object();

    private Object empty = new Object();

    private int putIndex;

    private int getIndex;

    public MyArrayBlockingQueue(int size) {
        items = new Object[size];
    }

    public void put(T t) {
        synchronized (full) {
            while (count == items.length) {
                try {
                    full.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (empty) {
            items[putIndex] = t;
            count++;
            putIndex++;
            if (putIndex == items.length) {
                putIndex = 0;
            }
            empty.notify();
        }
    }

    public T get() {
        synchronized (empty) {
            while (count == 0) {
                try {
                    empty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        synchronized (full) {
            Object result = items[getIndex];
            items[getIndex] = null;
            count--;
            getIndex++;
            if (getIndex == items.length) {
                getIndex = 0;
            }
            full.notify();
            return (T) result;
        }
    }
}
