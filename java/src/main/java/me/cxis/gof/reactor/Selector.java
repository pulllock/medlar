package me.cxis.gof.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * reactor模式中用来提供select方法用来从缓冲队列中查找出符合条件的event列表
 */
public class Selector {

    private java.util.concurrent.BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();

    private Object lock = new Object();

    public void addEvent(Event event) {
        boolean success = eventQueue.offer(event);
        if (success) {
            synchronized (lock) {
                lock.notify();
            }
        }
    }

    public List<Event> select() {
        return select(0);
    }

    public List<Event> select(int timeout) {
        if (timeout > 0) {
            if (eventQueue.isEmpty()) {
                synchronized (lock) {
                    try {
                        lock.wait(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        List<Event> events = new ArrayList<>();
        eventQueue.drainTo(events);
        return events;
    }
}
