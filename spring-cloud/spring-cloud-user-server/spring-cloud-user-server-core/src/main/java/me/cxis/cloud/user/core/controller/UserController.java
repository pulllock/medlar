package me.cxis.cloud.user.core.controller;

import me.cxis.cloud.user.api.controller.IUserController;
import me.cxis.cloud.user.api.model.User;
import me.cxis.cloud.user.api.query.UserQuery;
import me.cxis.cloud.user.core.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
public class UserController implements IUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Override
    public User queryUserById(Long userId) {
        LOGGER.info("query user by id, userId: {}", userId);
        UserQuery query = new UserQuery();
        query.setUserId(userId);
        return userService.queryUser(query);
    }
}
