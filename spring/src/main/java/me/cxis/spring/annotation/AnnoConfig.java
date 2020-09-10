package me.cxis.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnoConfig {

    @Bean
    public String annoBean() {
        return "";
    }
}
