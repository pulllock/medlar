package fun.pullock.spring.log.config;

import fun.pullock.spring.log.filter.LogAbstractRequestLoggingFilter;
import fun.pullock.spring.log.filter.LogOncePerRequestFilter;
import fun.pullock.spring.log.interceptor.LogHandlerInterceptor;
import fun.pullock.spring.log.interceptor.LogHandlerInterceptorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LogHandlerInterceptor logHandlerInterceptor;

    @Resource
    private LogHandlerInterceptorAdapter logHandlerInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logHandlerInterceptor)
                .addPathPatterns("/**")
        ;

        registry.addInterceptor(logHandlerInterceptorAdapter)
                .addPathPatterns("/**")
        ;
    }

    @Bean
    public LogOncePerRequestFilter logOncePerRequestFilter() {
        return new LogOncePerRequestFilter();
    }

    @Bean
    public LogAbstractRequestLoggingFilter logAbstractRequestLoggingFilter() {
        LogAbstractRequestLoggingFilter filter = new LogAbstractRequestLoggingFilter();
        filter.setIncludePayload(true);
        filter.setIncludeClientInfo(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeQueryString(true);
        return filter;
    }

    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludePayload(true);
        filter.setIncludeClientInfo(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeQueryString(true);
        return filter;
    }

    @Bean
    public ServletContextRequestLoggingFilter servletContextRequestLoggingFilter() {
        ServletContextRequestLoggingFilter filter = new ServletContextRequestLoggingFilter();
        filter.setIncludePayload(true);
        filter.setIncludeClientInfo(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeQueryString(true);
        return filter;
    }
}
