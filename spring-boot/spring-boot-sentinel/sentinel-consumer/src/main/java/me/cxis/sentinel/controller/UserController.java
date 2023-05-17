package me.cxis.sentinel.controller;

import me.cxis.sentinel.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getNameById")
    public String getNameById(@RequestParam Long userId) {
        return userService.getUserNameById(userId);
    }
}
