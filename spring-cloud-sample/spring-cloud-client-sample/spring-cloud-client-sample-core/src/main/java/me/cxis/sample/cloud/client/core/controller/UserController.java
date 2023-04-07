package me.cxis.sample.cloud.client.core.controller;

import me.cxis.sample.cloud.client.core.proxy.UserControllerProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserControllerProxy userControllerProxy;

    @GetMapping("/getUserName")
    public String getUserName(@RequestParam Long id) {
        return userControllerProxy.queryUserById(id).getName();
    }
}
