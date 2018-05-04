package me.cxis.dao.impl;

import me.cxis.dao.SendSmsRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by justdoit on 15-6-1.
 */
@Repository
public class SendSmsRedisDaoimpl implements SendSmsRedisDao {
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public void saveSmsToRedis() {
        System.out.println("saveSmsToRedis()");
        ShardedJedis jedis =  shardedJedisPool.getResource();
        //jedis.set("test", "test-0=-0-0-");
        long result = jedis.del("test");
        System.out.println(result);
        String getText = jedis.get("test");
        System.out.println(getText);
    }
}
