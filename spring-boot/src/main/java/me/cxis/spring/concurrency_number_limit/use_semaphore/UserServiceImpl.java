package me.cxis.spring.concurrency_number_limit.use_semaphore;

import me.cxis.spring.concurrency_number_limit.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author no name
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 最大访问并发数：5
     */

    private final static int MAX_CONCURRENCY_NUMBER = 5;

    private static Semaphore semaphore = new Semaphore(MAX_CONCURRENCY_NUMBER);

    @Override
    public String getUserNameById(long userId) {
        if (!semaphore.tryAcquire()) {
            LOGGER.warn("Get user name reached max concurrency number: {}", MAX_CONCURRENCY_NUMBER);
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
            semaphore.release();
        }

        return null;
    }
}
