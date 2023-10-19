package fun.pullock.i18n.controller;

import fun.pullock.i18n.lang.MessageSourceHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private MessageSourceHelper messageSourceHelper;

    @GetMapping("/hello")
    public String hello() {
        return messageSourceHelper.get("hello");
    }
}
