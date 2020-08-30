package me.cxis.spring.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class AnnotationManagerTest {

    @Resource
    private AnnotationManager annotationManager;

    @Test
    public void test() {
        annotationManager.test();
    }
}
