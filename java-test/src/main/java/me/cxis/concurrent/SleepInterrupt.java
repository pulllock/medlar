package me.cxis.concurrent;

/**
 * Created by cx on 7/31/16.
 */
public class SleepInterrupt implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("sleep 20s...");
            Thread.sleep(20000);
            System.out.println("woke up...");
        } catch (InterruptedException e) {
            System.out.println("intereupted while sleeping....");
            return;
        }
        System.out.println("leaving normally...");
    }

    public static void main(String[] args) {
        SleepInterrupt si = new SleepInterrupt();
        Thread t = new Thread(si);
        t.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main(),interrupting other thread...");
        t.interrupt();
        System.out.println("main(),leaving...");
    }
}
