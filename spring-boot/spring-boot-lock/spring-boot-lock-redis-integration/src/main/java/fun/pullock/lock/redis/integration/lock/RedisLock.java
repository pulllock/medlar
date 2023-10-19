package fun.pullock.lock.redis.integration.lock;

import jakarta.annotation.Resource;
import fun.pullock.lock.api.DistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class RedisLock implements DistributedLock {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    @Resource
    private RedisLockRegistry redisLockRegistry;

    @Override
    public boolean tryLock(String key, String clientId, long expireTime) {
        return tryLock(key);
    }

    @Override
    public boolean tryLock(String key, long expireTime) {
        return tryLock(key);
    }

    @Override
    public boolean tryLock(String key, String clientId) {
        return tryLock(key);
    }

    @Override
    public boolean tryLock(String key) {
        LOGGER.info("try lock for: {}", key);
        Lock lock = redisLockRegistry.obtain(key);
        boolean result = lock.tryLock();
        LOGGER.info("try lock for: {}, result: {}", key, result);

        return result;
    }

    @Override
    public boolean release(String key, String clientId) {
        return release(key);
    }

    @Override
    public boolean release(String key) {
        LOGGER.info("release lock: {}", key);
        Lock lock = redisLockRegistry.obtain(key);
        lock.unlock();
        LOGGER.info("release lock: {}, result: {}", key, true);
        return true;
    }
}
