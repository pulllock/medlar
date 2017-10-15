package me.cxis.thread;

/**
 * Created by cheng.xi on 2017-10-15 11:21.
 */
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable{

        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }
    }

    public static void ma   in(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }


    /*private ThreadLocal threadLocal1 = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "xxxxx";
        }
    };*/

}
