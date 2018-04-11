package me.cxis.concurrent.countdownlatch.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟程序启动之前检查网络，数据库和缓存是否启动。
 */
public class ApplicationStartupUtil {

    private static CountDownLatch countDownLatch;

    private static List<BaseHealthChecker> services;

    private ApplicationStartupUtil() {}

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception {
        countDownLatch = new CountDownLatch(3);
        services = new ArrayList<>();

        services.add(new NetworkHealthChecker(countDownLatch));
        services.add(new CacheHealthChecker(countDownLatch));
        services.add(new DatabaseHealthChecker(countDownLatch));

        ExecutorService executor = Executors.newFixedThreadPool(services.size());
        services.forEach(service -> executor.execute(service));

        countDownLatch.await();

        for (BaseHealthChecker service : services) {
            if (!service.isServiceUp()) {
                executor.shutdown();
                return false;
            }
        }
        executor.shutdown();
        return true;
    }
}
