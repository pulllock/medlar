package me.cxis.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DefaultAdvisorAutoProxyCreatorTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:defaultAdvisorAutoProxyCreator.xml");
        LoginService loginService = (LoginService) applicationContext.getBean("loginService");
        loginService.login("sdf");
    }
}
