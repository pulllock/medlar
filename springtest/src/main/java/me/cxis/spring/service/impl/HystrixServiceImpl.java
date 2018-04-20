package me.cxis.spring.service.impl;

import me.cxis.spring.hystrix.command.HystrixHelloCmommand;
import me.cxis.spring.hystrix.command.HystrixSemaphoreCmommand;
import me.cxis.spring.hystrix.command.HystrixThreadPoolCmommand;
import me.cxis.spring.service.HystrixService;
import org.springframework.stereotype.Service;

@Service("hystrixService")
public class HystrixServiceImpl implements HystrixService {

    @Override
    public String hello() {
        String name = Thread.currentThread().getName();
        String result = new HystrixHelloCmommand(name).execute();
        return result;
    }

    @Override
    public String hystrixThreadPool() {
        String name = Thread.currentThread().getName();
        String result = new HystrixThreadPoolCmommand(name).execute();
        return result;
    }

    @Override
    public String hystrixSemaphreo() {
        String name = Thread.currentThread().getName();
        String result = new HystrixSemaphoreCmommand(name).execute();
        return result;
    }
}
