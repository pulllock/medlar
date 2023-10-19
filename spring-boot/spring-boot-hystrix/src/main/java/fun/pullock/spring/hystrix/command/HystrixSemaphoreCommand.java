package fun.pullock.spring.hystrix.command;

import com.netflix.hystrix.*;

public class HystrixSemaphoreCommand extends HystrixCommand<String> {

    private final String name;

    public HystrixSemaphoreCommand(String name) {
        super(
            Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixSemaphoreGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("HystrixSemaphoreTest"))
            .andCommandPropertiesDefaults(
                HystrixCommandProperties
                .Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)
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
