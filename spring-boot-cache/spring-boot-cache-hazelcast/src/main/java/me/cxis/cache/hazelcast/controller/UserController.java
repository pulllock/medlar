package me.cxis.cache.hazelcast.controller;

import jakarta.annotation.Resource;
import me.cxis.cache.hazelcast.model.User;
import me.cxis.cache.hazelcast.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/queryById")
    public User queryById(@RequestParam("id") Long id) {
        return userService.queryById(id);
    }

    @GetMapping("/queryByName")
    public User queryById(@RequestParam("name") String name) {
        return userService.queryByName(name);
    }
}