package me.cxis.rate.limiting.limiter;

import java.time.LocalTime;

/**
 * 固定时间窗口计数
 */
public class FixedWindowCounterRateLimiter {

    /**
     * 计数器
     */
    private int counter;

    /**
     * 每个固定时间窗口内的限制请求数
     */
    private long permitsPerTimeWindow;

    /**
     * 时间窗口，单位毫秒，默认1秒
     */
    private long timeWindow = 1000;

    /**
     * 上一个时间窗口的开始时间
     */
    private long lastTimestamp = System.currentTimeMillis();

    /**
     * 构造方法，可以设置限流阈值和时间窗口
     *
     * @param permitsPerTimeWindow 限流阈值
     * @param timeWindow 时间窗口，单位毫秒
     */
    public FixedWindowCounterRateLimiter(long permitsPerTimeWindow, long timeWindow) {
        this.permitsPerTimeWindow = permitsPerTimeWindow;
        this.timeWindow = timeWindow;
    }

    public synchronized boolean tryAcquire() {
        // 请求时的时间
        long now = System.currentTimeMillis();

        // 上一个时间窗口已经过去，需要重置计数器和时间窗口的开始时间
        if (now - lastTimestamp > timeWindow) {
            counter = 0;
            lastTimestamp = now;
        }

        // 当前时间窗口内还未达到限流阈值
        if (counter < permitsPerTimeWindow) {
            counter ++;
            return true;
        }

        // 已经达到了阈值
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        // QPS = 2：1秒钟两个请求
        FixedWindowCounterRateLimiter rateLimiter = new FixedWindowCounterRateLimiter(2, 1000);

        int passedCount = 0;
        for (int i = 0; i < 10; i++) {
            LocalTime now = LocalTime.now();
            if (rateLimiter.tryAcquire()) {
                passedCount++;
                System.out.println(now + "处理请求");
            } else {
                System.out.println(now + "被限流了");
            }
        }

        System.out.println("第一次10个请求，通过数：" + passedCount);

        Thread.sleep(1000);

        passedCount = 0;
        for (int i = 0; i < 10; i++) {
            LocalTime now = LocalTime.now();
            if (rateLimiter.tryAcquire()) {
                passedCount++;
                System.out.println(now + "处理请求");
            } else {
                System.out.println(now + "被限流了");
            }
        }

        System.out.println("第二次10个请求，通过数：" + passedCount);

        Thread.sleep(1000);

        passedCount = 0;
        for (int i = 0; i < 10; i++) {
            LocalTime now = LocalTime.now();
            if (rateLimiter.tryAcquire()) {
                passedCount++;
                System.out.println(now + "处理请求");
            } else {
                System.out.println(now + "被限流了");
            }
        }

        System.out.println("第三次10个请求，通过数：" + passedCount);
    }
}
