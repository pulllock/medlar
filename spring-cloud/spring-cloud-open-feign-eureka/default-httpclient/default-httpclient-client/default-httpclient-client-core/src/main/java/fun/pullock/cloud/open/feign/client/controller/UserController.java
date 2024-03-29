package fun.pullock.cloud.open.feign.client.controller;

import fun.pullock.cloud.open.feign.server.api.model.User;
import jakarta.annotation.Resource;
import fun.pullock.cloud.open.feign.client.proxy.UserServiceProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceProxy userServiceProxy;

    @GetMapping("/getById")
    public User getById(@RequestParam Long id) {
        return userServiceProxy.queryUserById(id);
    }
}
