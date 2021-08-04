package me.cxis.spring.resource;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ResourceService {

    @Resource
    private ResourceDao resourceDao;

    public String getName() {
        return resourceDao.getName();
    }
}
