package fun.pullock.open.feign.async.server.core.controller;

import fun.pullock.cloud.open.feign.async.server.api.client.UserClient;
import fun.pullock.cloud.open.feign.async.server.api.model.User;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Enumeration;

@RestController
public class UserController implements UserClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Override
    public User queryById(Long userId) {
        LOGGER.info("Query user by id, userId: {}", userId);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.warn("Request attributes empty");
        } else {
            HttpServletRequest request = requestAttributes.getRequest();
            Enumeration<String> enumeration = request.getHeaderNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getHeader(name);
                LOGGER.info("Header: {} = {}", name, value);
            }
        }

        User user = new User();
        user.setUserId(userId);
        user.setCreateTime(LocalDateTime.now());
        return user;
    }
}
