package fun.pullock.lock.redisson.lock;

import jakarta.annotation.Resource;
import fun.pullock.lock.api.DistributedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLock implements DistributedLock {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedissonLock.class);

    @Value("${app.name}")
    private String appName;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public boolean tryLock(String key, String clientId, long expireTime) {
        return tryLock(key, expireTime);
    }

    @Override
    public boolean tryLock(String key, long expireTime) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("try lock for: {}, expire time: {} millis", key, expireTime);
        RLock lock = redissonClient.getLock(key);
        boolean result;
        try {
            result = lock.tryLock(expireTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("try lock for: {}, expire time: {} millis, result: {}", key, expireTime, result);

        return result;
    }

    @Override
    public boolean tryLock(String key, String clientId) {
        return tryLock(key);
    }

    @Override
    public boolean tryLock(String key) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("try lock for: {}, expire time: 1 minute", key);
        RLock lock = redissonClient.getLock(key);
        boolean result;
        try {
            result = lock.tryLock(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("try lock for: {}, expire time: 1 minute, result: {}", key, result);

        return result;
    }

    @Override
    public boolean release(String key, String clientId) {
        return release(key);
    }

    @Override
    public boolean release(String key) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("release lock: {}", key);
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
        LOGGER.info("release lock: {}, result: {}", key, true);
        return true;
    }
}
