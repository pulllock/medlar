package me.cxis.i18n.controller;

import me.cxis.i18n.lang.MessageSourceHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private MessageSourceHelper messageSourceHelper;

    @GetMapping("/hello")
    public String hello() {
        System.out.println(messageSourceHelper.getLanguage());
        System.out.println(messageSourceHelper.getCountry());
        return messageSourceHelper.get("hello");
    }
}
