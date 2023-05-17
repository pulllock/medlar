package me.cxis.spring.service.impl;

import me.cxis.spring.annotation.TrackTime;
import me.cxis.spring.dao.AopDao;
import me.cxis.spring.service.AopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service("aopService")
public class AopServiceImpl implements AopService {

    private Logger logger = LoggerFactory.getLogger(AopService.class);

    @Resource
    private AopDao aopDao;

    @Override
    public String calculateSomething() {
        String value = aopDao.getSomething();
        logger.info("In AopService - {}", value);
        return value;
    }

    @Override
    @TrackTime
    public String calculateSomething1() {
        String value = aopDao.getSomething1();
        logger.info("In AopService - {}", value);
        return value;
    }
}
