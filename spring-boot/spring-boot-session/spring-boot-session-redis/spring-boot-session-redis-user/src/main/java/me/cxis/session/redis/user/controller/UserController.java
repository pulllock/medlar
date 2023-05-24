package me.cxis.session.redis.user.controller;

import me.cxis.session.redis.user.session.SessionTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session/redis/user")
public class UserController {

    @GetMapping("/getUserId")
    public Long getUserId() {
        return SessionTool.getUserIdFromSession();
    }
}
