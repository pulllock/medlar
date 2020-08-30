package me.cxis.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnotationManager {

    /**
     * 注解在构造器上，构造器需要有参数
     * 无参构造器使用@Autowired注解会报错：Autowired annotation requires at least one argument
     */
    /*@Autowired
    public AnnotationManager() {

    }*/

    @Autowired(required = false)
    public AnnotationManager(AnnotationDao dao1) {

    }

    @Autowired(required = false)
    public AnnotationManager(AnnotationDao dao1, AnnotationDao dao2) {

    }


    public void test() {

    }
}
