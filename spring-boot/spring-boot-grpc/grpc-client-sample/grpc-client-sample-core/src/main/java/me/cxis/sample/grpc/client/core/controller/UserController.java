package me.cxis.sample.grpc.client.core.controller;

import jakarta.annotation.Resource;
import me.cxis.sample.grpc.client.core.proxy.UserServiceProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceProxy userServiceProxy;

    @GetMapping("/getUserName")
    public String getUserName(@RequestParam int id) {
        return userServiceProxy.getUserNameById(id);
    }
}
