package me.cxis.session.jdbc.mysql.user.controller;

import me.cxis.session.jdbc.mysql.user.session.SessionTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session/jdbc/mysql/user")
public class UserController {

    @GetMapping("/getUserId")
    public Long getUserId() {
        return SessionTool.getUserIdFromSession();
    }
}
