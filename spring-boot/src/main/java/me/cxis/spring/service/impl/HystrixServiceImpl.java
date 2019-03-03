package me.cxis.spring.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.cxis.spring.hystrix.command.HystrixHelloCmommand;
import me.cxis.spring.hystrix.command.HystrixSemaphoreCommand;
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
    public String hystrixSemaphore() {
        String name = Thread.currentThread().getName();
        String result = new HystrixSemaphoreCommand(name).execute();
        return result;
    }

    @Override
    @HystrixCommand(
        groupKey = "helloHystrixGroup",
        commandKey = "helloHystrix",
        fallbackMethod = "getFallback"
    )
    public String helloAnnotation() {
        String name = Thread.currentThread().getName();
        return name;
    }

    @Override
    @HystrixCommand(
        groupKey = "hystrixAnnotationThreadPoolGroup",
        commandKey = "hystrixAnnotationThreadPool",
        fallbackMethod = "getFallback",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10000")
        },
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "10"),
            @HystrixProperty(name = "maxQueueSize", value = "-1")
        }
    )
    public String hystrixAnnotationThreadPool() {
        String name = Thread.currentThread().getName();
        return name;
    }

    @Override
    @HystrixCommand(
        groupKey = "hystrixAnnotationSemaphoreGroup",
        commandKey = "hystrixAnnotationSemaphore",
        fallbackMethod = "getFallback",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10000"),
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10")
        }
    )
    public String hystrixAnnotationSemaphore() {
        String name = Thread.currentThread().getName();
        return name;
    }

    protected String getFallback() {
        return "access limit...";
    }
}
