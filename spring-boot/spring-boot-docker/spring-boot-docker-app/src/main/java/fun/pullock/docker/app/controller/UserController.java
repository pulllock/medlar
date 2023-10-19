package fun.pullock.docker.app.controller;

import fun.pullock.docker.app.model.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/queryById")
    public UserVO queryById(@RequestParam Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("参数错误");
        }

        UserVO user = new UserVO();
        user.setId(id);
        user.setName("xiaoming");
        return user;
    }
}
