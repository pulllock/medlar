package me.cxis.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/placeholder")
public class PlaceholderController {

    @Value("${user.name.prefix}")
    private String userName;

    @RequestMapping("/test")
    public String testPlaceholder() {
        return userName;
    }
}
