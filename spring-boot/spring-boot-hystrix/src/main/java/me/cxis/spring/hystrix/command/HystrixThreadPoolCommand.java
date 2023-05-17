package me.cxis.spring.hystrix.command;

import com.netflix.hystrix.*;

public class HystrixThreadPoolCommand extends HystrixCommand<String> {

    private final String name;

    public HystrixThreadPoolCommand(String name) {
        super(
            Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixThreadPoolGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("HystrixThreadPoolTest"))
            .andThreadPoolPropertiesDefaults(
                HystrixThreadPoolProperties
                .Setter()
                .withCoreSize(10)
                .withMaximumSize(20)
                .withMaxQueueSize(50)
            )
            .andCommandPropertiesDefaults(
                HystrixCommandProperties
                .Setter()
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(Integer.MAX_VALUE)
                .withExecutionTimeoutInMilliseconds(3000)
            )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello: " + name;
    }

    @Override
    protected String getFallback() {
        return "access limit...";
    }
}
