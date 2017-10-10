package me.cxis.concurrent.calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by cheng.xi on 14/12/2016.
 */
public class CalculateSomething {

    public int calculateSomething() throws InterruptedException, ExecutionException {

        //创建一个大小为CPU+1的线程池
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

        //线程数量
        int threadNumber = 100;

        //
        CountDownLatch doneSingle = new CountDownLatch(threadNumber);

        //计算结果
        List<Future<Integer>> resultList = new ArrayList<>();

        //创建100个计算的线程，提交
        for(int i = 0; i < threadNumber; i++){
            CalculateThread calculateThread = new CalculateThread(doneSingle);
            Future<Integer> future = pool.submit(calculateThread);
            resultList.add(future);
        }

        System.out.println("主线程：" + Thread.currentThread().getName() + " 正在等待所有计算线程计算完成！");
        //主线程等待所有计算线程计算完成
        doneSingle.await();
        System.out.println("主线程：" + Thread.currentThread().getName() + " 正在汇总所有计算结果！");

        System.out.println("计算结果列表总数：" + resultList.size());
        int count = 0;
        for(Future<Integer> future : resultList){
            count += future.get();
        }
        System.out.println("计算结果：" + count);
        System.out.println("主线程：" + Thread.currentThread().getName() + " 汇总计算结果完成，关闭线程池。");
        pool.shutdown();
        return 0;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        CalculateSomething calculateSomething = new CalculateSomething();
        calculateSomething.calculateSomething();
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行总时间是：" + (endTime - startTime));
    }
}
