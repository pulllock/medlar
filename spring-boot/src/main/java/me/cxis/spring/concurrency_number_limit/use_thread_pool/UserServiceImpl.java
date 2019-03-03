package me.cxis.spring.concurrency_number_limit.use_thread_pool;

import me.cxis.spring.concurrency_number_limit.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int POOL_SIZE = 5;

    private ExecutorService pool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 3, SECONDS, new SynchronousQueue<>());

    @Override
    public String getUserNameById(long userId) {
        try {
            Future<String> future = pool.submit(() -> {
                // 假装根据用户id查询名称
                int random = new Random().nextInt(1000);
                Thread.sleep(random);

                String userName = String.format("Xiao ming: %d", random);
                return userName;
            });
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
