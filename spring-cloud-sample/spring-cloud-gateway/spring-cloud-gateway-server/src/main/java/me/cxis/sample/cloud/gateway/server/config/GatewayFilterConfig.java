package me.cxis.sample.cloud.gateway.server.config;

import me.cxis.sample.cloud.gateway.server.filters.LoggingGatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关过滤器配置
 */
@Configuration
public class GatewayFilterConfig {

    @Bean
    public LoggingGatewayFilter loggingGatewayFilter() {
        return new LoggingGatewayFilter();
    }
}
