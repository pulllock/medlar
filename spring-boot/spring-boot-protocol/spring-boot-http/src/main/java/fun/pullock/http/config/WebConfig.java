package fun.pullock.http.config;

import fun.pullock.http.filter.LogOncePerRequestFilter;
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
