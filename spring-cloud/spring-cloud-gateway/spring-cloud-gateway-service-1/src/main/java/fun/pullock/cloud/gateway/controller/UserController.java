package fun.pullock.cloud.gateway.controller;

import fun.pullock.cloud.gateway.session.SessionTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUserName")
    public String getUserName() {
        return "user name from service 1, userId: " + SessionTool.getUid();
    }
}
