package me.cxis.distributedlock;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {

    /**
     * 尝试获取锁
     * @param key 键
     * @param clientId 请求锁的客户端
     * @param expireTime 过期时间
     * @param timeUnit 时间单位
     * @return 加锁结果
     */
    boolean tryLock(String key, String clientId, long expireTime, TimeUnit timeUnit);

    /**
     * 尝试获取锁，默认超时时间1分钟
     * @param key 键
     * @param clientId 请求锁的客户端
     * @return 加锁结果
     */
    boolean tryLock(String key, String clientId);

    /**
     * 释放锁
     * @param key 键
     * @param clientId 请求锁的客户端
     * @return 释放锁结果
     */
    boolean release(String key, String clientId);
}
