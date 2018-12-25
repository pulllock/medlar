package me.cxis.spring.concurrency_number_limit.use_counter;

import me.cxis.spring.concurrency_number_limit.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int MAX_NUMBER = 5;

    private static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public String getUserNameById(long userId) {
        int value = counter.incrementAndGet();
        if (value > MAX_NUMBER) {
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
            counter.decrementAndGet();
        }
        return null;
    }
}
