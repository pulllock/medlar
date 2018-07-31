package me.cxis.spring.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheTestManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheTestManager.class);

    @Cacheable(value = "testGetById", key = "#id + '_key'", unless = "#result == null")
    public String getById(long id) {
        LOGGER.info("read form db.....: {}", id);
        String value = id + "-value";
        return value;
    }
}
