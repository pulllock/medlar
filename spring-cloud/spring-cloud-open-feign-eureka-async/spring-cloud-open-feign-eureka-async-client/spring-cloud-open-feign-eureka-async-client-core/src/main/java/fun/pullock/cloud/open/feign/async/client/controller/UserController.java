package fun.pullock.cloud.open.feign.async.client.controller;

import fun.pullock.cloud.open.feign.async.client.proxy.UserClientServiceProxy;
import fun.pullock.cloud.open.feign.async.server.api.model.User;
import jakarta.annotation.Resource;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserClientServiceProxy userClientServiceProxy;

    @Resource
    private ThreadPoolExecutor userExecutor;

    /**
     * 同步调用Feign接口，配合FeignRequestInterceptor可以传递Header到Feign接口中
     * @param userId
     * @return
     */
    @GetMapping("/queryById/sync")
    public User queryById(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }
        return userClientServiceProxy.queryById(userId);
    }

    /**
     * 异步方式调用Feign接口，不能传递Header到Feign接口中
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async")
    public User queryByIdAsync(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }
        userClientServiceProxy.queryByIdAsync(userId);
        return new User();
    }

    /**
     * 异步方式调用Feign接口，不能传递Header到Feign接口中
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async-completable-future-async")
    public User queryByIdAsyncCompletableFutureAsync(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }

        CompletableFuture<User> future = CompletableFuture.supplyAsync(
                () -> userClientServiceProxy.queryById(userId)
        );

        future.handle((v, e) -> {
            if (e != null) {
                LOGGER.error("Error: ", e);
            } else {
                LOGGER.info("User: {}", v);
            }
            return v;
        });
        return new User();
    }

    /**
     * 异步方式调用Feign接口，不能传递Header到Feign接口中
     * @param userId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/queryById/async-completable-future-sync")
    public User queryByIdAsyncCompletableFutureSync(@RequestParam("userId") Long userId) throws ExecutionException, InterruptedException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }

        CompletableFuture<User> future = CompletableFuture.supplyAsync(
                () -> userClientServiceProxy.queryById(userId)
        );

        future.handle((v, e) -> {
            if (e != null) {
                LOGGER.error("Error: ", e);
            } else {
                LOGGER.info("User: {}", v);
            }
            return v;
        });
        return future.get();
    }

    /**
     * 异步方式调用Feign接口，不能传递Header到Feign接口中
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async-thread")
    public User queryByIdAsyncThread(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }

        new Thread(() -> {
            User user = userClientServiceProxy.queryById(userId);
            LOGGER.info("User: {}", user);
        }).start();
        return new User();
    }

    /**
     * 异步方式调用Feign接口，不能传递Header到Feign接口中
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async-executor")
    public User queryByIdAsyncExecutor(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }

        userExecutor.submit(() -> {
            User user = userClientServiceProxy.queryById(userId);
            LOGGER.info("User: {}", user);
        });

        return new User();
    }

    /**
     * 异步方式调用Feign接口，传递当前线程的RequestAttributes到异步线程中，异步线程立即执行
     * 该方式应该也有几率报错：The request object has been recycled and is no longer associated with this facade
     * 或：java.lang.NullPointerException: Cannot invoke "org.apache.tomcat.util.buf.MessageBytes.toString()" because the return value of "org.apache.tomcat.util.http.MimeHeaders.getName(int)" is nul
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async-pass-attributes")
    public User queryByIdAsyncPassAttributes(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }
        userClientServiceProxy.queryByIdAsync(userId, requestAttributes);
        return new User();
    }

    /**
     * 异步方式调用Feign接口，传递当前线程的RequestAttributes到异步线程中，并且异步线程不是立即执行，
     * 会报错：The request object has been recycled and is no longer associated with this facade
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async-pass-attributes-delay")
    public User queryByIdAsyncPassAttributesDelay(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }
        userClientServiceProxy.queryByIdAsyncDelay(userId, requestAttributes);
        return new User();
    }

    /**
     * 异步方式调用Feign接口
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async-completable-future-async-pass-attributes")
    public User queryByIdAsyncCompletableFutureAsyncPassAttributes(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }

        CompletableFuture<User> future = CompletableFuture.supplyAsync(
                () -> {
                    RequestContextHolder.setRequestAttributes(requestAttributes);
                    return userClientServiceProxy.queryById(userId);
                }
        );

        future.handle((v, e) -> {
            if (e != null) {
                LOGGER.error("Error: ", e);
            } else {
                LOGGER.info("User: {}", v);
            }
            return v;
        });
        return new User();
    }

    /**
     * 异步方式调用Feign接口，传递当前线程的RequestAttributes到异步线程中，并且异步线程不是立即执行，
     * 会报错：The request object has been recycled and is no longer associated with this facade
     * @param userId
     * @return
     */
    @GetMapping("/queryById/async-completable-future-async-pass-attributes-delay")
    public User queryByIdAsyncCompletableFutureAsyncPassAttributesDelay(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }

        CompletableFuture<User> future = CompletableFuture.supplyAsync(
                () -> {
                    RequestContextHolder.setRequestAttributes(requestAttributes);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return userClientServiceProxy.queryById(userId);
                }
        );

        future.handle((v, e) -> {
            if (e != null) {
                LOGGER.error("Error: ", e);
            } else {
                LOGGER.info("User: {}", v);
            }
            return v;
        });
        return new User();
    }

    @GetMapping("/queryById/async-completable-future-async-pass-attributes-delay-AsyncContext")
    public User queryByIdAsyncCompletableFutureAsyncPassAttributesDelayAsyncContext(@RequestParam("userId") Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Enumeration<String> enumeration = requestAttributes.getRequest().getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            LOGGER.info("Header: {} = {}", name, value);
        }

        AsyncContext asyncContext = request.startAsync();

        CompletableFuture<User> future = CompletableFuture.supplyAsync(
                () -> {
                    RequestContextHolder.setRequestAttributes(requestAttributes);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    User user = userClientServiceProxy.queryById(userId);
                    asyncContext.complete();
                    return user;
                }
        );

        future.handle((v, e) -> {
            if (e != null) {
                LOGGER.error("Error: ", e);
            } else {
                LOGGER.info("User: {}", v);
            }
            return v;
        });
        return new User();
    }
}
