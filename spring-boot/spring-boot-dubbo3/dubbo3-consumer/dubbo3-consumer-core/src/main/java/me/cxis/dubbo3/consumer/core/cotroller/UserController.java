package me.cxis.dubbo3.consumer.core.cotroller;

import me.cxis.dubbo3.consumer.core.json.Json;
import me.cxis.dubbo3.provider.api.service.DubboUserServiceGrpc.IUserService;
import me.cxis.dubbo3.provider.api.service.UserServiceOuterClass.User;
import me.cxis.dubbo3.provider.api.service.UserServiceOuterClass.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @GetMapping("/query")
    public String queryUser(@RequestParam Integer userId) {
        UserQuery userQuery = UserQuery.newBuilder().setId(userId).build();
        User user = userService.getUserByQuery(userQuery);
        LOGGER.info("query user result: {}", Json.toJsonString(user));
        return user.getName();
    }
}
