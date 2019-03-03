package me.cxis.spring.aop.example;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class TestAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("after " + o1.getClass().getSimpleName() + "." + method.getName() + "()");
    }
}
