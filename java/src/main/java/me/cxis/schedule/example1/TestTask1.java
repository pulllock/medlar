package me.cxis.schedule.example1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestTask1 implements Runnable {

    @Override
    public void run() {
        System.out.println("任务开始执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        long start = System.currentTimeMillis();
        int random = new Random().nextInt(10);
        try {
            Thread.sleep(random * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务执行花费时间：" + (System.currentTimeMillis() - start));
        System.out.println("任务结束执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
