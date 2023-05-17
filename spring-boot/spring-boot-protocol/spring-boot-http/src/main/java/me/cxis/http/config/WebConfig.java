package me.cxis.http.config;

import me.cxis.http.filter.LogOncePerRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LogOncePerRequestFilter logOncePerRequestFilter() {
        return new LogOncePerRequestFilter();
    }

}
