package me.cxis.exception;

/**
 * Created by cheng.xi on 2017-03-15 09:12.
 */
public class LogThread implements Runnable {

    private int count;

    public LogThread(int count){
        this.count = count;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("线程" + Thread.currentThread().getName() + "正在运行，一直在收集日志。");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 5){
                try {
                    Thread.sleep(1000);
                    //throw new Exception(Thread.currentThread().getName() + "出现了异常");
                    int i = count / 0;
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
