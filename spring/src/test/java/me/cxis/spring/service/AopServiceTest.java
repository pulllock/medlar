package me.cxis.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopServiceTest {

    private Logger logger = LoggerFactory.getLogger(AopServiceTest.class);

    @Resource
    private AopService aopService;

    @Test
    public void testCalculateSomething() {
        logger.info(aopService.calculateSomething());
        logger.info(aopService.calculateSomething1());
    }
}
