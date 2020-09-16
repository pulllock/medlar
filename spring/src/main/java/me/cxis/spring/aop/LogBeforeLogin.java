package me.cxis.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class LogBeforeLogin implements MethodBeforeAdvice {
    
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("autoproxy:有人要登录了。。。");
    }
}