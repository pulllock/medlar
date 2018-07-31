package me.cxis.spring.service.impl;

import me.cxis.spring.manager.CacheTestManager;
import me.cxis.spring.service.CacheService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Resource
    private CacheTestManager cacheTestManager;

    @Override
    public String getById(long id) {
        return cacheTestManager.getById(id);
    }
}
