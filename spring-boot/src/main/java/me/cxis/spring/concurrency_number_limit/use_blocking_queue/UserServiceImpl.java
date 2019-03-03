package me.cxis.spring.concurrency_number_limit.use_blocking_queue;

import me.cxis.spring.concurrency_number_limit.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int MAX_NUMBER = 5;

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    @Override
    public String getUserNameById(long userId) {
        if (!queue.offer(1)) {
            LOGGER.warn("Get user name reached max concurrency number: {}", MAX_NUMBER);
            return null;
        }

        try {
            // 假装根据用户id查询名称
            int random = new Random().nextInt(1000);
            Thread.sleep(random);

            String userName = String.format("Xiao ming: %d", random);
            return userName;
        } catch (Exception e) {
            LOGGER.warn("Get user name error: {}", e);
        } finally {
            queue.poll();
        }
        return null;
    }
}
