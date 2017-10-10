package me.cxis.concurrent.calculate;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by cheng.xi on 14/12/2016.
 */
public class CalculateThread implements Callable<Integer>{

    private CountDownLatch doneSingle;

    public CalculateThread(CountDownLatch doneSingle) {
        this.doneSingle = doneSingle;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("线程" + Thread.currentThread().getName() + " 正在计算......");
        long calculateTime = (long)(Math.random() * 2000);
        Thread.sleep(calculateTime);
        doneSingle.countDown();
        System.out.println("线程" + Thread.currentThread().getName() + " 计算完成，耗费时间：" + calculateTime);
        return (int) calculateTime;
    }
}
