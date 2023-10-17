package me.cxis.aop.aspect;

import me.cxis.aop.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* me.cxis.aop..*.*Service.*(..))")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("before, method: {}, args{}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After("logPointCut()")
    public void logAfter(JoinPoint joinPoint) {
        LOGGER.info("after, method: {}, args: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("around before, method: {}, args: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
        Object ret = joinPoint.proceed();
        LOGGER.info(
                "around after, method: {}, args: {}, return: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs(), ret
        );
        if (ret instanceof User) {
            User user = (User) ret;
            if (user.getId() == 2L) {
                ret = new User(2L, "new-user", 30);
            }
        }
        LOGGER.info(
                "around after, method: {}, args: {}, return: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs(), ret
        );
        return ret;
    }

    @AfterReturning(value = "logPointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info(
                "after returning before, method: {}, args: {}, result: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs(), result
        );

        if (result instanceof User) {
            User user = (User) result;
            if (user.getId() == 1L) {
                user.setName("new-name");
            }
        }

        LOGGER.info(
                "after returning after, method: {}, args: {}, result: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs(), result
        );
    }

    @AfterThrowing(value = "logPointCut()", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        LOGGER.info(
                "after throwing, method: {}, args: {}, exception: ",
                joinPoint.getSignature().getName(), joinPoint.getArgs(), throwable
        );
    }
}
