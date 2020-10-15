package me.cxis.spring.cache;

import me.cxis.spring.aop.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserCacheManagerTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserCacheManager userCacheManager = (UserCacheManager) applicationContext.getBean("userCacheManager");
        System.out.println(userCacheManager.getUserByName("sdf"));
        System.out.println(userCacheManager.getUserByName("sdf"));
    }
}
