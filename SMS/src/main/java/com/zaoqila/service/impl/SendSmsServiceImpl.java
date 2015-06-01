package com.zaoqila.service.impl;

import com.zaoqila.dao.SendSmsRedisDao;
import com.zaoqila.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by justdoit on 15-6-1.
 */
@Service
public class SendSmsServiceImpl implements SendSmsService {

    @Autowired
    private SendSmsRedisDao sendSmsRedisDao;

    public String sendSms() {
        System.out.println("------sendSmsService.sendSms()");
        sendSmsRedisDao.saveSmsToRedis();
        return null;
    }
}
