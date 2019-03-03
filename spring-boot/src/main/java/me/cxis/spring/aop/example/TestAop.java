package me.cxis.spring.aop.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestAop {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("spring-test/src/main/java/me/cxis/spring/aop/example/beans.xml");
        TestTarget target = (TestTarget) applicationContext.getBean("testAop");
        target.test();
        System.out.println("======");
        target.test2();
    }
}
