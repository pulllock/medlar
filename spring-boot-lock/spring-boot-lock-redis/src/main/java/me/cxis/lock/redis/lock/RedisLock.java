package me.cxis.lock.redis.lock;

import jakarta.annotation.Resource;
import me.cxis.lock.api.DistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Component
public class RedisLock implements DistributedLock {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    @Value("${app.name}")
    private String appName;

    private final static DefaultRedisScript<Long> UNLOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call('get', KEYS[1] == ARGV[1]) then return redis.call('del', KEYS[1])  else return 0 end",
            Long.class
    );

    private static final Long SUCCESS = 1L;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean tryLock(String key, String clientId, long expireTime) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("try lock for: {}, client id: {}, expire time: {} millis", key, clientId, expireTime);
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, 1, Duration.ofMillis(expireTime));
        LOGGER.info("try lock for: {}, client id: {}, expire time: {} millis, result: {}", key, clientId, expireTime, result);

        return Boolean.TRUE.equals(result);
    }

    @Override
    public boolean tryLock(String key, long expireTime) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("try lock for: {}, expire time: {} millis", key, expireTime);
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, 1, Duration.ofMillis(expireTime));
        LOGGER.info("try lock for: {}, expire time: {} millis, result: {}", key, expireTime, result);

        return Boolean.TRUE.equals(result);
    }

    @Override
    public boolean tryLock(String key, String clientId) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("try lock for: {}, client id: {}, expire time: 1 minute", key, clientId);
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, clientId, Duration.ofMinutes(1));
        LOGGER.info("try lock for: {}, client id: {}, expire time: 1 minute, result: {}", key, clientId, result);
        return Boolean.TRUE.equals(result);
    }

    @Override
    public boolean tryLock(String key) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("try lock for: {}, expire time: 1 minute", key);
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, 1, Duration.ofMinutes(1));
        LOGGER.info("try lock for: {}, expire time: 1 minute, result: {}", key, result);

        return Boolean.TRUE.equals(result);
    }

    @Override
    public boolean release(String key, String clientId) {
        key = String.format("%s::%s", appName, key);
        Long result = redisTemplate.execute(UNLOCK_LUA_SCRIPT, Collections.singletonList(key), clientId);
        return SUCCESS.equals(result);
    }

    @Override
    public boolean release(String key) {
        key = String.format("%s::%s", appName, key);

        LOGGER.info("release lock: {}", key);
        Boolean result = redisTemplate.delete(key);
        LOGGER.info("release lock: {}, result: {}", key, result);

        return Boolean.TRUE.equals(result);
    }
}
