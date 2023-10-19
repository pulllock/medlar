package fun.pullock.session.jdbc.mysql.user.controller;

import fun.pullock.session.jdbc.mysql.user.model.User;
import fun.pullock.session.jdbc.mysql.user.session.SessionTool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session/jdbc/mysql")
public class LoginController {

    @GetMapping("/login")
    public boolean login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 校验用户信息

        Long useId = 1000000L;

        User user = new User();
        user.setUsername(username);
        user.setId(useId);

        // 登陆成功后，设置用户信息到session中
        SessionTool.addUserIdToSession(useId);

        return true;
    }

    @GetMapping("/logout")
    public boolean logout() {
        // 直接使session失效
        // SessionTool.getSession().invalidate();

        // 将session中的用户信息清除
        SessionTool.addUserIdToSession(0L);
        return true;
    }
}
