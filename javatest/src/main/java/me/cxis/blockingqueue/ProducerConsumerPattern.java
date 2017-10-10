package me.cxis.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by cx on 7/22/16.
 */
public class ProducerConsumerPattern {

    public static void main(String[] args) {
        BlockingQueue sharedQueue = new LinkedBlockingDeque();
        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue));

        prodThread.start();
        consThread.start();
    }
}
