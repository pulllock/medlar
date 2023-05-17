package me.cxis.spring.controller;

import me.cxis.spring.event.custom.CustomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/event")
public class EventController {

    @Resource
    private CustomService customService;

    @GetMapping("/publish")
    public String publish(String name) {
        customService.publish();
        return name;
    }
}
