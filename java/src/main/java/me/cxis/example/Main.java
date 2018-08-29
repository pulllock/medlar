package me.cxis.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by cheng.xi on 2017-03-22 23:09.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DownloadFileCallable downloadFileCallable = new DownloadFileCallable();
        FutureTask<String> futureTask = new FutureTask<String>(downloadFileCallable);

        ExecutorService executor = Executors.newFixedThreadPool(1);
        System.out.println(Thread.currentThread().getName() + "：" + "在执行的时候，这里要下载一个文件");
        executor.execute(futureTask);
        System.out.println(Thread.currentThread().getName() + "：" + "继续做其他的事情。。。");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "：" + "做完了其他事情，等着下载完成");
        while(true){

            if(futureTask.isDone()){
                System.out.println(Thread.currentThread().getName() + "：" + futureTask.get());
                System.out.println(Thread.currentThread().getName() + "：" + "下载好了，可以处理文件了。。。");
                executor.shutdown();
                return;
            }

        }

    }
}
