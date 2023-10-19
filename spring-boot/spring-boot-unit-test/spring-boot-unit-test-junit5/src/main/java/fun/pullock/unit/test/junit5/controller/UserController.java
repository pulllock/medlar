package fun.pullock.unit.test.junit5.controller;

import fun.pullock.unit.test.junit5.manager.UserManager;
import fun.pullock.unit.test.junit5.model.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserManager userManager;

    @GetMapping("/queryById")
    public UserVO queryById(@RequestParam Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("参数错误");
        }
        return userManager.queryById(id);
    }
}
