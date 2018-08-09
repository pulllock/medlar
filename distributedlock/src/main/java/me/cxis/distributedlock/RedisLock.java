package me.cxis.distributedlock;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock implements DistributedLock {

    /**
     * NX 键不存在时才进行设置
     * XX 键存在是才进行设置
     */
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * EX 设置键的过期时间为second秒
     * PX 设置键的过期时间为millisecond毫秒
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * redis操作成功后返回OK
     */
    private static final String OK = "OK";

    /**
     * 释放锁成功后返回值
     */
    private static final Long SUCCESS = 1L;

    @Resource
    private Jedis jedis;

    @Override
    public boolean tryLock(String key, String clientId, long expireTime, TimeUnit timeUnit) {
        String result = jedis.set(key, clientId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, timeUnit.toMillis(expireTime));
        if (OK.equals(result)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(String key, String clientId) {
        return tryLock(key, clientId, 1, TimeUnit.MINUTES);
    }

    @Override
    public boolean release(String key, String clientId) {
        String script = "if redis.call('get', KEYS[1] == ARGV[1]) then return redis.call('del', KEYS[1])  else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(clientId));
        if (SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
