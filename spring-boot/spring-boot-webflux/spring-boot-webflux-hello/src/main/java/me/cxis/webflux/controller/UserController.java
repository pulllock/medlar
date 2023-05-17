package me.cxis.webflux.controller;

import me.cxis.webflux.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webflux")
public class UserController {

    @GetMapping("/user")
    public Mono<User> getUser() {
        User user = new User();
        user.setName("webflux name");
        user.setAge(3);
        return Mono.just(user);
    }
}
