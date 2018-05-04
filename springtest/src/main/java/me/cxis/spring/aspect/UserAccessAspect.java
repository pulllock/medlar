package me.cxis.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAccessAspect {

    private Logger logger = LoggerFactory.getLogger(UserAccessAspect.class);

    @Before("execution(* me.cxis.spring.dao.*.*(..))")
    public void beforeDaoAccess(JoinPoint joinPoint) {
        logger.info("Check for user access before Dao");
        logger.info("Allowed execution for {}", joinPoint);
    }

    @AfterReturning(value = "execution(* me.cxis.spring.service.*.*(..))", returning = "result")
    public void afterReturningForService(JoinPoint joinPoint, Object result) {
        logger.info("[{}] returned with value: {}", joinPoint, result);
    }

    @After("execution(* me.cxis.spring.service.*.*(..))")
    public void afterForService(JoinPoint joinPoint) {
        logger.info("after execution of {}", joinPoint);
    }
}
