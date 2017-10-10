package me.cxis.service.impl;

import me.cxis.dao.RedisDao;
import me.cxis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by justdoit on 15-6-2.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisDao redisDao;

    public void sendSms() {
        redisDao.sendSms();
        System.out.println(redisDao.getSms());
    }
}
