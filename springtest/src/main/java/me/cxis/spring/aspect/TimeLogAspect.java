package me.cxis.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeLogAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(TimeLogAspect.class);

    @Around("@annotation(me.cxis.spring.annotation.Timed) && execution(public * * (..))")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object value = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        LOGGER.info(
            "{}.{} took {} ms",
            joinPoint.getSignature().getDeclaringType().getSimpleName(),
            joinPoint.getSignature().getName(),
            endTime - startTime
        );

        return value;
    }
}
