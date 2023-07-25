package me.cxis.cache.caffeine.config;

import com.github.benmanes.caffeine.cache.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static me.cxis.cache.caffeine.cache.CacheNames.USER_BY_ID;
import static me.cxis.cache.caffeine.cache.CacheNames.USER_BY_NAME;

/**
 * 缓存配置
 */
@Configuration
@EnableCaching
public class CacheConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public CacheManagerCustomizer<CaffeineCacheManager> cacheManagerCustomizer() {
        return cacheManager -> {
            cacheManager.setCaffeine(
                    Caffeine.newBuilder()
                            // 最后一次写入或者访问后经过固定的时间过期，设置为1分钟
                            .expireAfterWrite(1, TimeUnit.MINUTES)
                            // 初始缓存空间大小
                            .initialCapacity(100)
                            // 缓存最大条数
                            .maximumSize(1000)
                            .evictionListener((key, value, cause) -> {
                                LOGGER.info("key: {} evicted, value: {}, cause: {}", key, value, cause);
                            })
                            .removalListener((key, value, cause) -> {
                                LOGGER.info("key: {} removed, value: {}, cause: {}", key, value, cause);
                            })
                            // 记录统计数据
                            .recordStats()
                            // 可自定义线程池
                            // .executor()
            );
            // true表示允许缓存null值，false表示不允许缓存null值，默认为true
            cacheManager.setAllowNullValues(true);

            // 注册自定义缓存
            cacheManager.registerCustomCache(
                    USER_BY_ID,
                    Caffeine.newBuilder()
                            .refreshAfterWrite(30, TimeUnit.SECONDS)
                            .initialCapacity(100)
                            .maximumSize(10000)
                            .evictionListener((key, value, cause) -> {
                                LOGGER.info("key: {} evicted, value: {}, cause: {}", key, value, cause);
                            })
                            .removalListener((key, value, cause) -> {
                                LOGGER.info("key: {} removed, value: {}, cause: {}", key, value, cause);
                            })
                            .build(new CacheLoader<>() {
                                @Override
                                public @Nullable Object load(Object key) {
                                    LOGGER.info("load key: {}", key);
                                    return null;
                                }
                            })
            );
            cacheManager.registerCustomCache(
                    USER_BY_NAME,
                    Caffeine.newBuilder()
                            .expireAfterWrite(20, TimeUnit.SECONDS)
                            .initialCapacity(100)
                            .maximumSize(10000)
                            .evictionListener((key, value, cause) -> {
                                LOGGER.info("key: {} evicted, value: {}, cause: {}", key, value, cause);
                            })
                            .removalListener((key, value, cause) -> {
                                LOGGER.info("key: {} removed, value: {}, cause: {}", key, value, cause);
                            })
                            .build(new CacheLoader<>() {
                                @Override
                                public @Nullable Object load(Object key) {
                                    LOGGER.info("load key: {}", key);
                                    return null;
                                }
                            })
            );
        };
    }

}
