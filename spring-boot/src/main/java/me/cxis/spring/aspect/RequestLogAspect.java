package me.cxis.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class RequestLogAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * * (..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes())
            .getRequest();

        Object value = joinPoint.proceed();

        LOGGER.info(
            "{} {} from {}",
            request.getMethod(),
            request.getRequestURI(),
            request.getRemoteAddr()
        );

        return value;
    }
}
