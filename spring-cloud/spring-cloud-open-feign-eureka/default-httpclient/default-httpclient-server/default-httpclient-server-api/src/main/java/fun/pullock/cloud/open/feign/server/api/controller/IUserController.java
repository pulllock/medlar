package fun.pullock.cloud.open.feign.server.api.controller;

import fun.pullock.cloud.open.feign.server.api.model.common.Result;
import fun.pullock.cloud.open.feign.server.api.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserController {

    @GetMapping("/user/queryByUserId")
    Result<User> queryUserById(@RequestParam("userId") Long userId);
}
