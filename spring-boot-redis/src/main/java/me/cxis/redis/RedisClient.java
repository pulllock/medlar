package me.cxis.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class RedisClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return false;
        }
    }

    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return false;
        }
    }

    public boolean lPush(String key, String value) {
        try {
            Long length = redisTemplate.opsForList().leftPush(key, value);
            return length > 0;
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return false;
        }
    }

    public boolean sAdd(String key, String value) {
        try {
            redisTemplate.opsForSet().add(key, value);
            return true;
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return false;
        }
    }

    public boolean incrBy(String key, long delta) {
        try {
            Long result = redisTemplate.opsForValue().increment(key, delta);
            return result != null && result > 0;
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return false;
        }
    }

    public Long zCard(String key) {
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return 0L;
        }
    }

    public boolean zAdd(String key, Long value, double score) {
        try {
            Boolean result = redisTemplate.opsForZSet().add(key, String.valueOf(value), score);
            return result != null && result;
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return false;
        }
    }

    public Set<Object> zRevRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return null;
        }
    }

    public Long zRevRank(String key, String value) {
        try {
            return redisTemplate.opsForZSet().reverseRank(key, value);
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return null;
        }
    }

    public Double zScore(String key, String value) {
        try {
            return redisTemplate.opsForZSet().score(key, value);
        } catch (Exception e) {
            LOGGER.error("redis error, cause: ", e);
            return null;
        }
    }
}
