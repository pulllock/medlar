package me.cxis.json.gson.controller;

import me.cxis.json.gson.model.User;
import me.cxis.json.gson.model.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/gson")
public class GsonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsonController.class);

    @PostMapping("/test")
    public User queryUser(@RequestBody UserQuery query) {
        LOGGER.info("query: {}", query);
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setBirthday(LocalDateTime.now());

        return user;
    }
}
