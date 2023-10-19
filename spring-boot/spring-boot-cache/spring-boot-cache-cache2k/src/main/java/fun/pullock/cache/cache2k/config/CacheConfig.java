package fun.pullock.cache.cache2k.config;

import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.Cache2kBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.MINUTES;
import static fun.pullock.cache.cache2k.cache.CacheNames.USER_BY_ID;
import static fun.pullock.cache.cache2k.cache.CacheNames.USER_BY_NAME;

/**
 * 缓存配置
 */
@Configuration
@EnableCaching
public class CacheConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public SpringCache2kCacheManager cacheManager() {
        return new SpringCache2kCacheManager()
                .defaultSetup(
                        cache2kBuilder -> cache2kBuilder
                                .expireAfterWrite(5, MINUTES)
                                .entryCapacity(100)
                ).addCaches(
                        cache2kBuilder -> cache2kBuilder.name(USER_BY_ID).expireAfterWrite(1, MINUTES).entryCapacity(1000),
                        cache2kBuilder -> cache2kBuilder.name(USER_BY_NAME).expireAfterWrite(2, MINUTES).entryCapacity(500)
                );
    }

    /**
     * 可以通过此配置默认设置
     * @return
     */
    // @Bean
    public Cache2kBuilderCustomizer cache2kBuilderCustomizer() {
        return builder -> builder
                .expireAfterWrite(5, MINUTES)
                .entryCapacity(100)
                ;
    }

}
