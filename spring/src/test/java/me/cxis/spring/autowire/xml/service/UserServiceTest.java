package me.cxis.spring.autowire.xml.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    @Test
    public void testGetUserName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowireXml.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.getUserName(1L));
    }
}
