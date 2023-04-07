package me.cxis.cloud.user.api.controller;

import me.cxis.cloud.user.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("spring-cloud-user-server-core")
public interface IUserController {

    @GetMapping("/user/queryByUserId")
    User queryUserById(@RequestParam("userId") Long userId);
}
