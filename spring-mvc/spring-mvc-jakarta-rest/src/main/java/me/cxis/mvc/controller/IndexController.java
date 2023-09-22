package me.cxis.mvc.controller;

import me.cxis.mvc.model.DefaultMessage;
import me.cxis.mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public DefaultMessage index() {
        String userName = userService.getUserName(1L);
        LOGGER.info("userName: {}", userName);
        DefaultMessage message = new DefaultMessage();
        message.setMsg("hello...");
        return message;
    }
}
