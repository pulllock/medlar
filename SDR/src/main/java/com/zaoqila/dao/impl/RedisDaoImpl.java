package com.zaoqila.dao.impl;

import com.zaoqila.base.RedisGeneratorDao;
import com.zaoqila.dao.RedisDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;

/**
 * Created by justdoit on 15-6-2.
 */
@Repository
public class RedisDaoImpl extends RedisGeneratorDao implements RedisDao {
    public void sendSms() {
        redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set("testKey".getBytes(),"testValue".getBytes());
                return 1l;
            }
        });
    }

    public String getSms() {
        return redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return new String(redisConnection.get("testKey".getBytes()));
            }
        }).toString();
    }
}
