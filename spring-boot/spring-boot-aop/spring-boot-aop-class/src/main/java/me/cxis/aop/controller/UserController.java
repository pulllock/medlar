package me.cxis.aop.controller;

import jakarta.annotation.Resource;
import me.cxis.aop.model.User;
import me.cxis.aop.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getById")
    public User getById(@RequestParam("id") Long id) {
        return userService.getById(id);
    }
}
