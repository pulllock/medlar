package me.cxis.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by cx on 7/22/16.
 */
public class Consumer implements Runnable{

    private final BlockingQueue sharedQueue;

    public Consumer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Consumed: " + sharedQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
