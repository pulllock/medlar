package me.cxis.spring;

import me.cxis.spring.event.custom.CustomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration({"classpath:applicationContext.xml"})
public class CustomServiceTest {

    @Resource
    private CustomService customService;

    @Test
    public void testEvent() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        customService = (CustomService)context.getBean("customService");
        customService.publish();
    }
}
