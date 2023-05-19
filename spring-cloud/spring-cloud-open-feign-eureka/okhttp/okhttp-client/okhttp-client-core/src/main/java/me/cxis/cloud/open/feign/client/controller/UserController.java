package me.cxis.cloud.open.feign.client.controller;

import jakarta.annotation.Resource;
import me.cxis.cloud.open.feign.client.proxy.UserServiceProxy;
import me.cxis.cloud.open.feign.server.api.model.User;
import me.cxis.cloud.open.feign.server.api.query.UserQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceProxy userServiceProxy;

    @GetMapping("/getById")
    public User getById(@RequestParam Long id) {
        return userServiceProxy.queryUserById(id);
    }

    @GetMapping("/getByCondition")
    public User getByCondition() {
        UserQuery userQuery = new UserQuery();
        userQuery.setCreateTime(LocalDateTime.now());
        return userServiceProxy.queryUserByCondition(userQuery);
    }

    @GetMapping("/getByCreateTime")
    public User getByCreateTime(@RequestParam String createTime) {
        UserQuery userQuery = new UserQuery();
        userQuery.setCreateTime(LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse(createTime)));
        return userServiceProxy.queryUserByCondition(userQuery);
    }
}
