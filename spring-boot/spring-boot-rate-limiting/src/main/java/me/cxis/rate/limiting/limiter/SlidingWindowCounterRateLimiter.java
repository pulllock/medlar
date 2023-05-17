package me.cxis.rate.limiting.limiter;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口计数
 */
public class SlidingWindowCounterRateLimiter {

    /**
     * 每个时间窗口内的限制请求数
     */
    private long permitsPerTimeWindow;

    /**
     * 子窗口个数
     */
    private int subTimeWindowCount;

    /**
     * 每个子窗口的时间，单位毫秒
     */
    private long subTimeWindow;

    /**
     * 时间窗口，单位毫秒，默认1秒
     */
    private long timeWindow = 1000;

    /**
     * 计数器，key为当前时间窗口的开始时间，value为当前时间窗口内的请求计数
     */
    private Window[] windows;

    public SlidingWindowCounterRateLimiter(long permitsPerTimeWindow, long timeWindow, int subTimeWindowCount) {
        this.permitsPerTimeWindow = permitsPerTimeWindow;
        this.timeWindow = timeWindow;
        this.subTimeWindowCount = subTimeWindowCount;
        this.subTimeWindow = timeWindow / subTimeWindowCount;
        this.windows = new Window[subTimeWindowCount];
        long now = System.currentTimeMillis();
        for (int i = 0; i < windows.length; i++) {
            windows[i] = new Window(now, new AtomicInteger(0));
        }
    }

    public synchronized boolean tryAcquire() {
        // 获取当前时间
        long now = System.currentTimeMillis();

        // 计算当前时间窗口
        int currentSubWindow = (int) (now % timeWindow / subTimeWindow);

        // 更新当前窗口计数，重置过期窗口计数
        int sum = 0;
        for (int i = 0; i < windows.length; i++) {
            Window window = windows[i];
            if ((now - window.getTime()) > timeWindow) {
                window.getCount().set(0);
                window.setTime(now);
            }

            if (currentSubWindow == i && window.getCount().get() < permitsPerTimeWindow) {
                window.getCount().incrementAndGet();
            }

            sum = sum + window.getCount().get();
        }

        return sum <= permitsPerTimeWindow;
    }

    private class Window {
        private long time;

        private AtomicInteger count;

        public Window(long time, AtomicInteger count) {
            this.time = time;
            this.count = count;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public AtomicInteger getCount() {
            return count;
        }

        public void setCount(AtomicInteger count) {
            this.count = count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // QPS = 2：1秒钟两个请求
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(2, 1000, 10);

        int passedCount = 0;
        for (int i = 0; i < 20; i++) {
            Thread.sleep(300);
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
        for (int i = 0; i < 20; i++) {
            Thread.sleep(300);
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
        for (int i = 0; i < 20; i++) {
            Thread.sleep(300);
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
