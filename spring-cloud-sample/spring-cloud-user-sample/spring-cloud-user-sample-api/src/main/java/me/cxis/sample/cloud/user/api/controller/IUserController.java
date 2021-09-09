package me.cxis.sample.cloud.user.api.controller;

import me.cxis.sample.cloud.user.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("spring-cloud-user-sample")
@RequestMapping("/user")
public interface IUserController {

    @GetMapping("/queryByUserId")
    User queryUserById(@RequestParam("userId") Long userId);
}
