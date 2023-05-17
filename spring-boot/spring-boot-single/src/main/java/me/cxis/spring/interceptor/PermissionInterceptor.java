package me.cxis.spring.interceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequestMapping requestMapping = ((HandlerMethod) handler).getMethodAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                RestController restController = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RestController.class);
                if (restController != null) {
                    System.out.println("rest controller: " + handler);
                }
            }
        }
        return true;
    }
}
