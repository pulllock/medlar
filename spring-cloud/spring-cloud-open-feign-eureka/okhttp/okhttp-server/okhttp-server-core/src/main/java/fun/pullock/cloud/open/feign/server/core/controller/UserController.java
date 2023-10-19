package fun.pullock.cloud.open.feign.server.core.controller;

import jakarta.annotation.Resource;
import fun.pullock.cloud.open.feign.server.api.controller.IUserController;
import fun.pullock.cloud.open.feign.server.api.model.User;
import fun.pullock.cloud.open.feign.server.api.model.common.Result;
import fun.pullock.cloud.open.feign.server.api.query.UserQuery;
import fun.pullock.cloud.open.feign.server.core.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Override
    public Result<User> queryUserById(Long userId) {
        LOGGER.info("query user by id, userId: {}", userId);
        UserQuery query = new UserQuery();
        query.setUserId(userId);
        User user = userService.queryUser(query);
        Result<User> result = new Result<>();
        result.setData(user);
        return result;
    }

    @Override
    public Result<User> queryByCondition(UserQuery query) {
        LOGGER.info("query user by condition, query: {}", query);
        User user = userService.queryUser(query);
        Result<User> result = new Result<>();
        result.setData(user);
        return result;
    }
}
