package me.cxis.cache.couchbase.controller;

import jakarta.annotation.Resource;
import me.cxis.cache.couchbase.service.UserService;
import me.cxis.cache.couchbase.model.User;
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