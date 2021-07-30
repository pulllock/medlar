package me.cxis.spring.utils;

import me.cxis.spring.autowire.xml.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtilXmlTest {

    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowireXml.xml");
        ApplicationContextUtilXml utilXml = (ApplicationContextUtilXml) context.getBean("applicationContextUtilXml");
        UserService userService = (UserService) utilXml.getBean("userService");
        System.out.println(userService.getUserName(1L));
    }
}
