package me.cxis.sample.nacos.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${user.name.prefix: default_prefix_}")
    private String userNamePrefix;

    @RequestMapping("/queryNamePrefix")
    public String queryNamePrefix() {
        return userNamePrefix;
    }
}
