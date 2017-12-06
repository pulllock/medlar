package me.cxis.blockingqueue.arrayblockingqueue;


import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Message message;
            while(true) {
                Thread.sleep(10);
                message = queue.take();
                System.out.println(message.getMessage());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
