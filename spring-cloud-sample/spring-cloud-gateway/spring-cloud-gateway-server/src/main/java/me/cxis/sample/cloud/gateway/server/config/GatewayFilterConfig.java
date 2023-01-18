package me.cxis.sample.cloud.gateway.server.config;

import me.cxis.sample.cloud.gateway.server.exception.GatewayErrorAttributes;
import me.cxis.sample.cloud.gateway.server.exception.GlobalErrorExceptionHandler;
import me.cxis.sample.cloud.gateway.server.filters.AuthenticationGatewayFilter;
import me.cxis.sample.cloud.gateway.server.filters.IpKeyResolver;
import me.cxis.sample.cloud.gateway.server.filters.LoggingGatewayFilter;
import me.cxis.sample.cloud.gateway.server.filters.RateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 网关过滤器配置
 */
@Configuration
public class GatewayFilterConfig {

    @Bean
    public LoggingGatewayFilter loggingGatewayFilter() {
        return new LoggingGatewayFilter();
    }

    @Bean
    public AuthenticationGatewayFilter authenticationGatewayFilter() {
        return new AuthenticationGatewayFilter();
    }

    @Bean
    public IpKeyResolver ipKeyResolver() {
        return new IpKeyResolver();
    }

    @Bean
    public RateLimiterGatewayFilterFactory rateLimiterGatewayFilterFactory(RateLimiter rateLimiter,
                                                                           KeyResolver resolver) {
        return new RateLimiterGatewayFilterFactory(rateLimiter, resolver);
    }

    @Bean
    public GatewayErrorAttributes gatewayErrorAttributes() {
        return new GatewayErrorAttributes();
    }

    /**
     * 全局异常处理
     * 优先级低于默认的ResponseStatusExceptionHandler
     * @return
     */
    @Bean
    @Order(-1)
    public GlobalErrorExceptionHandler globalErrorExceptionHandler() {
        return new GlobalErrorExceptionHandler();
    }
}
