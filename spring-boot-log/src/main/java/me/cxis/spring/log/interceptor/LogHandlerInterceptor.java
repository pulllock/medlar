package me.cxis.spring.log.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class LogHandlerInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String parameters = getRequestParameters(request);
        String ip = getIp(request);
        String contentType = request.getContentType();

        LOGGER.info("Request, ip: {}, content type: {}, method: {}, URI: {}, parameters: {}", ip, contentType, method, uri, parameters);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private String getIp(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            return ipFromHeader;
        }

        return request.getRemoteAddr();
    }

    private String getRequestParameters(HttpServletRequest request) {
        StringBuffer result = new StringBuffer();
        Enumeration<String> names = request.getParameterNames();
        if (names != null && names.hasMoreElements()) {
            result.append("?");
        }

        while (names.hasMoreElements()) {
            if (result.length() > 1) {
                result.append("&");
            }

            String current = names.nextElement();
            result.append(current + "=");
            result.append(request.getParameter(current));
        }
        return result.toString();
    }
}
