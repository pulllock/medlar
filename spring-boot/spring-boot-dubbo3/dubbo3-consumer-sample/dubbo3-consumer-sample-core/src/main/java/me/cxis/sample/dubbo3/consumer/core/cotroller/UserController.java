package me.cxis.sample.dubbo3.consumer.core.cotroller;

import com.alibaba.fastjson.JSON;
import me.cxis.sample.dubbo3.provider.api.service.DubboUserServiceGrpc.IUserService;
import me.cxis.sample.dubbo3.provider.api.service.UserServiceOuterClass.User;
import me.cxis.sample.dubbo3.provider.api.service.UserServiceOuterClass.UserQuery;
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
        LOGGER.info("query user result: {}", JSON.toJSONString(user));
        return user.getName();
    }
}
