package fun.pullock.cache.infinispan.config;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static fun.pullock.cache.infinispan.cache.CacheNames.USER_BY_ID;
import static fun.pullock.cache.infinispan.cache.CacheNames.USER_BY_NAME;

/**
 * 缓存配置
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${spring.application.name}")
    private String appName;


    @Bean
    public InfinispanCacheConfigurer infinispanCacheConfigurer() {
        return manager -> {
            manager.defineConfiguration(
                    USER_BY_ID,
                    new ConfigurationBuilder()
                            .memory().maxCount(1000)
                            .expiration().lifespan(1, TimeUnit.MINUTES)
                            .build()
            );

            manager.defineConfiguration(
                    USER_BY_NAME,
                    new ConfigurationBuilder()
                            .memory().maxCount(1000)
                            .expiration().lifespan(2, TimeUnit.MINUTES)
                            .build()
            );
        };
    }
}
