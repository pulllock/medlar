package com.zaoqila.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Created by justdoit on 15-6-2.
 */
public abstract class RedisGeneratorDao {

    @Autowired
    public RedisTemplate redisTemplate;

}
