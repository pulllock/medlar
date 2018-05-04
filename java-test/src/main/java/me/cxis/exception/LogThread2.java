package me.cxis.exception;

import java.util.concurrent.Callable;

/**
 * Created by cheng.xi on 2017-03-15 09:38.
 */
public class LogThread2 implements Callable {
    @Override
    public Object call() throws Exception {
        while (true){
            System.out.println("线程" + Thread.currentThread().getName() + "正在运行，一直在收集日志。");
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "休息100ms");
                Thread.sleep(100);
                System.out.println("线程" + Thread.currentThread().getName() + "休息100ms结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "休息1000ms");
                Thread.sleep(1000);
                System.out.println("线程" + Thread.currentThread().getName() + "休息1000ms结束");
                //throw new Exception(Thread.currentThread().getName() + "出现了异常");
                System.out.println("线程" + Thread.currentThread().getName() + "开始计算");
                int i = 3 / 0;
                System.out.println("线程" + Thread.currentThread().getName() + "计算完成");
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
