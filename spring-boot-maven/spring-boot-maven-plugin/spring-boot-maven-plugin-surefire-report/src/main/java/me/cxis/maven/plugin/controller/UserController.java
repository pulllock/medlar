package me.cxis.maven.plugin.controller;

import me.cxis.maven.plugin.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/queryByUserId")
    public User queryByUserId(@RequestParam Long userId) {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("my name");
        user.setSex(1);
        return user;
    }

    @GetMapping("/queryByUserName")
    public User queryByUserName(@RequestParam String userName) {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("my name");
        user.setSex(1);
        return user;
    }
}
