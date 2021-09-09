package me.cxis.sample.cloud.user.core.service.impl;

import me.cxis.sample.cloud.user.api.model.User;
import me.cxis.sample.cloud.user.api.query.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public User queryUser(UserQuery query) {
        LOGGER.info("query user, query: {}", query);
        User user = new User();
        user.setUserId(111L);
        user.setName("xxxxName");
        return user;
    }
}
