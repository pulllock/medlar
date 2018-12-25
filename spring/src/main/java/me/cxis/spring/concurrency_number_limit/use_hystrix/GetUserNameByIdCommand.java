package me.cxis.spring.concurrency_number_limit.use_hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;

public class GetUserNameByIdCommand extends HystrixCommand<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserNameByIdCommand.class);

    private static final int MAX_NUMBER = 5;

    private long userId;

    protected GetUserNameByIdCommand(long userId) {
        super(Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserServiceGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("getUserNameById"))
            .andCommandPropertiesDefaults(
                HystrixCommandProperties
                    .Setter()
                    .withExecutionIsolationStrategy(SEMAPHORE)
                    .withExecutionIsolationSemaphoreMaxConcurrentRequests(MAX_NUMBER)
                    .withFallbackIsolationSemaphoreMaxConcurrentRequests(10000)
                    .withExecutionTimeoutInMilliseconds(3000)
            )
        );
        this.userId = userId;
    }

    @Override
    protected String run() throws Exception {
        // 假装根据用户id查询名称
        int random = new Random().nextInt(1000);
        Thread.sleep(random);

        String userName = String.format("Xiao ming: %d", random);
        return userName;
    }

    @Override
    protected String getFallback() {
        LOGGER.warn("Get user name reached max concurrency number: {}", MAX_NUMBER);
        return null;
    }
}
