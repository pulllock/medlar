package me.cxis.gof.reactor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * reactor的事件接收类，负责初始化selector和接收缓冲队列
 */
public class Acceptor implements Runnable {

    private int port;

    private Selector selector;

    // 代表serversocket，模拟外部输入请求队列
    private java.util.concurrent.BlockingQueue<InputSource> sourceQueue = new LinkedBlockingQueue<>();

    public Acceptor(int port, Selector selector) {
        this.port = port;
        this.selector = selector;
    }

    public void addNewConnection(InputSource source) {
        sourceQueue.offer(source);
    }

    @Override
    public void run() {
        while (true) {
            InputSource source = null;
            try {
                source = sourceQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if (source != null) {
            Event acceptEvent = new Event();
            acceptEvent.setSource(source);
            acceptEvent.setType(EventType.ACCEPT);
            selector.addEvent(acceptEvent);
        }
        }
    }

    public int getPort() {
        return port;
    }
}
