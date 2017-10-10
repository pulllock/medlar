package me.cxis.concurrent.send;

import java.util.concurrent.*;

/**
 * Created by cheng.xi on 14/12/2016.
 */
public class SendSomethingToServer {

    public String sendSomethingToServer(){
        System.out.println(Thread.currentThread().getName() + "开始模拟向服务器发送文件......");
        String send = "请服务器给我返回一个视频！";

        ExecutorService executorService = Executors.newCachedThreadPool();

        SentThread sentThread = new SentThread(send);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
        completionService.submit(sentThread);

        System.out.println(Thread.currentThread().getName() + "已经将文件发送给服务器...");
        System.out.println(Thread.currentThread().getName() + "主线程做其他事情去了...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "主线程做完了其他事情，开始获取服务器返回的东西...");
        String result = "";
        try {
            result = completionService.take().get();
            System.out.println(Thread.currentThread().getName() + "服务器返回的结果是：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        return result;
    }

    public static void main(String[] args) {
        SendSomethingToServer sendSomethingToServer = new SendSomethingToServer();
        String result = sendSomethingToServer.sendSomethingToServer();
        System.out.println("Main:" + result);
    }
}
