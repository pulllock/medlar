package me.cxis.spring.autowire.xml;

import me.cxis.spring.autowire.xml.dao.UserDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class IgnoreDependencyType implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // 加上这个的话，UserDao就不会被注入到其他的Bean中
        // configurableListableBeanFactory.ignoreDependencyType(UserDao.class);
    }
}
