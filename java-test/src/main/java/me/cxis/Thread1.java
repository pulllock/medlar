package me.cxis;

/**
 * Created by cheng.xi on 5/30/16.
 */
public class Thread1 implements Runnable {
    public void run(){
        synchronized(this){
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + " loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread ta = new Thread(t1,"A");
        Thread tb = new Thread(t1,"B");
        ta.start();
        tb.start();
    }
}
