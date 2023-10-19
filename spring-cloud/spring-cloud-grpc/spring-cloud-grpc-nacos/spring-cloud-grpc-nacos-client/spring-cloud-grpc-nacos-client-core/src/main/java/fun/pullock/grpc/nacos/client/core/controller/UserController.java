package fun.pullock.grpc.nacos.client.core.controller;

import fun.pullock.grpc.nacos.client.core.proxy.UserServiceProxy;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceProxy userServiceProxy;

    @GetMapping("/getUserName")
    public String getUserName(@RequestParam int id) {
        return userServiceProxy.getUserNameById(id);
    }
}
