package me.cxis.spring.configuration;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConfigManager configManager = (ConfigManager)context.getBean("configManager");
        System.out.println(configManager.getConfig());

        configManager = (ConfigManager)context.getBean("configManager");
        System.out.println(configManager.getConfig());
    }
}
