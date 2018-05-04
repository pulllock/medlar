package me.cxis.concurrent.send;

import java.util.concurrent.Callable;

/**
 * Created by cheng.xi on 14/12/2016.
 */
public class SentThread implements Callable<String>{
    private String send;
    public SentThread(String send) {
        this.send = send;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "开始发送东西到服务器...");
        System.out.println(Thread.currentThread().getName() + "需要发送的内容：" + send);
        Thread.sleep((long)(Math.random() * 1000));
        String result = "success";
        System.out.println(Thread.currentThread().getName() + "发送完成......服务器返回结果：" + result);
        return result;
    }
}
