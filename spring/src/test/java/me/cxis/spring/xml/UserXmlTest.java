package me.cxis.spring.xml;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class UserXmlTest {

    @Test
    public void testUserXml() {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        UserXml userXml = (UserXml)xmlBeanFactory.getBean("userXml");
        System.out.println("userXml: " + userXml);
        BeanDefinition beanDefinition = xmlBeanFactory.getBeanDefinition("userXml");
        System.out.println("beanDefinition: " + beanDefinition);
        System.out.println("beanDefinition source: " + beanDefinition.getSource());
    }
}
