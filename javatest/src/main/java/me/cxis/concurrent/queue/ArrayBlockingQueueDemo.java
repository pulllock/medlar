package me.cxis.concurrent.queue;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;

/**
 * Created by chengxi on 23/11/2016.
 * 多个线程同时操作并且遍历queue
 */
public class ArrayBlockingQueueDemo {
    private static Queue<String> queue = new ArrayBlockingQueue<String>(20);

    public static void main(String[] args) {
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    public static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while(i++ < 6){
                String val = Thread.currentThread().getName() + "-" + i;
                queue.add(val);
                printAll();
            }
        }

        private void printAll() {
            String value;
            Iterator iterator = queue.iterator();
            while(iterator.hasNext()){
                value = (String) iterator.next();
                System.out.printf(value + ",");
            }
            System.out.println();
        }
    }
}
