package me.cxis.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerLogAspect {

    @Pointcut("execution(public * me.cxis.spring.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        // 记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String msg = String.format(
                "Request url: %s, remote address: %s, class: %s.%s, method: %s, args: %s",
                request.getRequestURL().toString(),
                request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                request.getMethod(),
                Arrays.toString(joinPoint.getArgs())
        );
        System.out.println(msg);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void afterReturning(Object ret) {
        // 处理完之后返回内容
        System.out.println("After returning: " + ret);
    }

    @AfterThrowing("webLog()")
    public void afterThrowing(JoinPoint joinPoint) {
        // 后置异常通知
        System.out.println("异常。。。");
    }

    @After("webLog()")
    public void after(JoinPoint joinPoint) {
        // 方法正常与否，都会在在最后执行
        System.out.println("方法最后执行");
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("环绕开始");

        try {
            Object o = joinPoint.proceed();
            System.out.println("环绕的proceed: " + o);
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
