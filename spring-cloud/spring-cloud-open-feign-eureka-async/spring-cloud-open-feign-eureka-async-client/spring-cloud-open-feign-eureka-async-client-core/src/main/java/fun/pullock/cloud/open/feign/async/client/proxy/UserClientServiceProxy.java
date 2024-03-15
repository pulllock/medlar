package fun.pullock.cloud.open.feign.async.client.proxy;

import fun.pullock.cloud.open.feign.async.server.api.model.User;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class UserClientServiceProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserClientServiceProxy.class);

    @Resource
    private UserClientService userClientService;

    public User queryById(Long userId) {
        LOGGER.info("Query by id, userId: {}", userId);
        try {
            User result = userClientService.queryById(userId);
            LOGGER.info("Query by id done, result: {}", result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Async
    public void queryByIdAsync(Long userId) {
        LOGGER.info("Query by id async, userId: {}", userId);
        try {
            User result = userClientService.queryById(userId);
            LOGGER.info("Query by id async done, result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void queryByIdAsync(Long userId, RequestAttributes requestAttributes) {
        RequestContextHolder.setRequestAttributes(requestAttributes);
        LOGGER.info("Query by id async, userId: {}", userId);
        try {
            User result = userClientService.queryById(userId);
            LOGGER.info("Query by id async done, result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void queryByIdAsyncDelay(Long userId, RequestAttributes requestAttributes) {
        RequestContextHolder.setRequestAttributes(requestAttributes);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Query by id async, userId: {}", userId);
        try {
            User result = userClientService.queryById(userId);
            LOGGER.info("Query by id async done, result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
