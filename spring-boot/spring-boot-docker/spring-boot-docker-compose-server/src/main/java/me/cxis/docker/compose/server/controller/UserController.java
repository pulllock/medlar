package me.cxis.docker.compose.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/queryById")
    public String queryById(@RequestParam Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("参数错误");
        }

        return "xiao ming: " + id;
    }
}
