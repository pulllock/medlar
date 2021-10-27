package me.cxis.jctools.queue;

import org.jctools.queues.MpscUnboundedArrayQueue;

import java.util.Queue;

public class MpscQueueTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new MpscUnboundedArrayQueue<>(4);

        for (int i = 0; i < 20; i++) {
            queue.offer(i);
        }
    }
}
