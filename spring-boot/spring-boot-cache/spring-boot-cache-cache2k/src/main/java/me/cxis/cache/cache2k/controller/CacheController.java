package me.cxis.cache.cache2k.controller;

import jakarta.annotation.Resource;
import org.cache2k.extra.spring.SpringCache2kCache;
import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.cache2k.operation.CacheControl;
import org.cache2k.operation.CacheInfo;
import org.cache2k.operation.CacheStatistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private SpringCache2kCacheManager cacheManager;

    @GetMapping("/statistics")
    public Map<String, CacheStatistics> statistics() {
        Map<String, CacheStatistics> statistics = new HashMap<>();
        for (Map.Entry<String, SpringCache2kCache> cacheEntry : cacheManager.getCacheMap().entrySet()) {
            statistics.put(cacheEntry.getKey(), CacheControl.of(cacheEntry.getValue().getNativeCache()).sampleStatistics());
        }

        return statistics;
    }

    @GetMapping("/info")
    public Map<String, Map<String, Object>> info() {
        Map<String, Map<String, Object>> infoMap = new HashMap<>();
        for (Map.Entry<String, SpringCache2kCache> cacheEntry : cacheManager.getCacheMap().entrySet()) {
            CacheInfo cacheInfo = CacheInfo.of(cacheEntry.getValue().getNativeCache());
            Map<String, Object> info = new HashMap<>();
            info.put("expiryAfterWriteTicks", cacheInfo.getExpiryAfterWriteTicks());
            info.put("capacityLimit", cacheInfo.getCapacityLimit());
            info.put("entryCapacity", cacheInfo.getEntryCapacity());
            info.put("createdTime", cacheInfo.getCreatedTime());
            info.put("isStatisticsEnabled", cacheInfo.isStatisticsEnabled());
            info.put("name", cacheInfo.getName());
            infoMap.put(cacheEntry.getKey(), info);
        }

        return infoMap;
    }
}
