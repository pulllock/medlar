package me.cxis.mq.kafka.controller;

import me.cxis.mq.kafka.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/register")
    public Boolean register(@RequestParam String name) {
        if (!StringUtils.hasLength(name)) {
            throw new RuntimeException("参数错误");
        }

        return userService.register(name);
    }
}
