package me.cxis.queue.blockingqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 */
public class Producer implements Runnable{

    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Message message = new Message(i + "");
            try {
                Thread.sleep(i);
                queue.put(message);
                System.out.println("Produced:" + message.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
