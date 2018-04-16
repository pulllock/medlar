package me.cxis.concurrent.executor.example1;

public class ThreadRunnable implements Runnable {

    private String name;

    public ThreadRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start: " + name);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
