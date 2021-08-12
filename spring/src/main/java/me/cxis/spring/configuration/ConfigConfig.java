package me.cxis.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigConfig {

    @Bean
    public ConfigDao configDao() {
        return new ConfigDao();
    }

    @Bean
    public ConfigManager configManager() {
        ConfigDao configDao = configDao();
        System.out.println("configDao hash: " + configDao.hashCode());
        return new ConfigManager(configDao);
    }
}
