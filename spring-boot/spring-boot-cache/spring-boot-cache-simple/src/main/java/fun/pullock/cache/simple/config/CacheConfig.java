package fun.pullock.cache.simple.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 自定义缓存配置
     * @return
     */
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return cacheManager -> {
            // true表示缓存一份副本，false表示缓存引用，默认为false
            cacheManager.setStoreByValue(true);
            // true表示允许缓存null值，false表示不允许缓存null值，默认为true
            cacheManager.setAllowNullValues(true);
        };
    }
}
