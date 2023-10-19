package fun.pullock.cache.infinispan.controller;

import jakarta.annotation.Resource;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.MemoryConfiguration;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.embedded.provider.SpringEmbeddedCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    @GetMapping("/config/global")
    public GlobalConfiguration globalConfiguration() {
        return springEmbeddedCacheManager.getNativeCacheManager().getCacheManagerConfiguration();
    }

    @GetMapping("/config/all")
    public Map<String, MemoryConfiguration> allConfiguration() {
        Collection<String> cacheNames = springEmbeddedCacheManager.getCacheNames();
        EmbeddedCacheManager cacheManager= springEmbeddedCacheManager.getNativeCacheManager();
        Map<String, MemoryConfiguration> configurations = new HashMap<>();
        for (String cacheName : cacheNames) {
            configurations.put(cacheName, cacheManager.getCacheConfiguration(cacheName).memory());
        }
        return configurations;
    }
}
