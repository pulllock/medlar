package fun.pullock.aop.controller;

import jakarta.annotation.Resource;
import fun.pullock.aop.model.User;
import fun.pullock.aop.service.UserService;
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
