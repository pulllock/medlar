package me.cxis.example;

import java.util.concurrent.Callable;

/**
 * Created by cheng.xi on 2017-03-22 22:49.
 */
public class DownloadFileCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "：" + "正在下载文件。。。");
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + "：" + "下载完成，花费了5秒多。。。");
        return "这是下载的文件id";
    }
}
