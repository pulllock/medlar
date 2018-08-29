package me.cxis.spring;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

        simpleCacheManager.setCaches(
            Arrays.asList(
                testGetById()
            )
        );

        return simpleCacheManager;
    }

    @Bean
    public CaffeineCache testGetById() {
        return new CaffeineCache(
            "testGetById",
            Caffeine
                .newBuilder()
                .recordStats()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .maximumSize(10)
                .build()
        );
    }
}
