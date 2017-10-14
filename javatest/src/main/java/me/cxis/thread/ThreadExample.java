package me.cxis.thread;

/**
 * Created by cheng.xi on 2017-10-14 21:49.
 */
public class ThreadExample {


    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();

        MyThread myThread = new MyThread();
        myThread.start();

        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable running");
            }
        };
        Thread thread2 = new Thread(runnable, "new Thread");
        thread2.start();

        Thread.currentThread();

        String threadName = Thread.currentThread().getName();

        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("" + 1) {
                public void run() {
                    System.out.println("Thread:" + getName());
                }
            }.start();
        }
    }





}
