package fun.pullock.lock.api;

/**
 * 锁接口
 */
public interface DistributedLock {

    /**
     * 尝试获取锁
     * @param key 键
     * @param clientId 请求锁的客户端
     * @param expireTime 过期时间，单位：毫秒
     * @return true-成功获取到锁 false-未获取到锁
     */
    boolean tryLock(String key, String clientId, long expireTime);

    /**
     * 尝试获取锁
     * @param key
     * @param expireTime 过期时间，单位：毫秒
     * @return true-成功获取到锁 false-未获取到锁
     */
    boolean tryLock(String key, long expireTime);

    /**
     * 尝试获取锁，默认超时时间1分钟
     * @param key 键
     * @param clientId 请求锁的客户端
     * @return true-成功获取到锁 false-未获取到锁
     */
    boolean tryLock(String key, String clientId);

    /**
     * 尝试获取锁，默认超时时间1分钟
     * @param key
     * @return true-成功获取到锁 false-未获取到锁
     */
    boolean tryLock(String key);

    /**
     * 释放锁
     * @param key 键
     * @param clientId 请求锁的客户端
     * @return true-成功释放锁 false-释放锁失败
     */
    boolean release(String key, String clientId);

    /**
     * 释放锁
     * @param key 键
     * @return true-成功释放锁 false-释放锁失败
     */
    boolean release(String key);
}
