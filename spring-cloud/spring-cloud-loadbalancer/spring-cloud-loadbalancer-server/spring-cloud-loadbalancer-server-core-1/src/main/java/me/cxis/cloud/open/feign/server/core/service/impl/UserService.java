package me.cxis.cloud.open.feign.server.core.service.impl;

import me.cxis.cloud.open.feign.server.api.model.User;
import me.cxis.cloud.open.feign.server.api.query.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public User queryUser(UserQuery query) {
        LOGGER.info("query user, query: {}", query);
        User user = new User();
        user.setUserId(111L);
        user.setName("xxxxName from server 1");
        user.setBirthday(LocalDateTime.now());
        return user;
    }
}
