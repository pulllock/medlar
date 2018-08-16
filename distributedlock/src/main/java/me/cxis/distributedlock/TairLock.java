package me.cxis.distributedlock;

import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class TairLock implements DistributedLock {

    /**
     * Tair的命名空间
     */
    private static final int NAMESPACE = 100;

    /**
     * 加锁时的版本号
     */
    private static final int VERSION = 1;

    @Resource
    private TairManager tairManager;

    @Override
    public boolean tryLock(String key, String clientId, long expireTime, TimeUnit timeUnit) {
        ResultCode resultCode = tairManager.put(
            NAMESPACE,
            key,
            0,
            VERSION,
            (int) timeUnit.toSeconds(expireTime)
            );

        if (ResultCode.SUCCESS.equals(resultCode)) {
            // 获取成功
            return true;
        } else if (ResultCode.VERERROR.equals(resultCode)) {
            // 已被其他线程获取
            return false;
        } else {
            // 其他错误
            return false;
        }
    }

    @Override
    public boolean tryLock(String key, String clientId) {
        return tryLock(key, clientId, 60 * 60, TimeUnit.SECONDS);
    }

    @Override
    public boolean release(String key, String clientId) {
        ResultCode resultCode = tairManager.delete(NAMESPACE, key);
        if (ResultCode.SUCCESS.equals(resultCode)) {
            return true;
        }
        return false;
    }
}
