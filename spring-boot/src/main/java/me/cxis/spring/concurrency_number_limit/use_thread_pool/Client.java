package me.cxis.spring.concurrency_number_limit.use_thread_pool;

import me.cxis.spring.concurrency_number_limit.UserService;

import java.util.concurrent.CountDownLatch;

public class Client {

    public static final int THREAD_NUMBER = 100;

    private static final CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);

    static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    String userName = userService.getUserNameById(1);
                    System.out.println(userName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            countDownLatch.countDown();
        }
    }
}
