package me.cxis.spring;

import com.taobao.tair.TairManager;
import me.cxis.spring.cache.TairCache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Arrays;

// @Configuration
// @EnableCaching
public class TairCacheConfig {

    private int namespace = 1;

    @Resource
    private TairManager tairManager;

    @Bean
    public CacheManager cacheManager () {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(
            Arrays.asList(
                testGetById()
            )
        );

        return simpleCacheManager;
    }

    @Bean
    public TairCache testGetById() {
        return new TairCache(
            "testGetById",
            namespace,
            60 * 60 * 12,
            tairManager
        );
    }
}
