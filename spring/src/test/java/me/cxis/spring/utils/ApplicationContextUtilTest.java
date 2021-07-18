package me.cxis.spring.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ApplicationContextUtilTest {

    @Test
    public void testGetBeanDefinition() {
        BeanDefinition beanDefinition = ApplicationContextUtil.getBeanDefinition("userCacheManager");
        System.out.println("beanDefinition: " + beanDefinition);
        System.out.println("beanDefinition source: " + beanDefinition.getSource());
    }
}
