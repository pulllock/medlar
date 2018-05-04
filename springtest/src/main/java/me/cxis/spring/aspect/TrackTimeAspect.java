package me.cxis.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TrackTimeAspect {

    private Logger logger = LoggerFactory.getLogger(TrackTimeAspect.class);

    @Around("@annotation(me.cxis.spring.annotation.TrackTime)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        logger.info("Time taken by {} is: {}", joinPoint, (endTime - startTime));
    }
}
