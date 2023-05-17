package me.cxis.spring.controller;

import me.cxis.spring.groovy.MessageProcessManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping(value = "/groovy")
public class GroovyController {

    @Resource
    private MessageProcessManager messageProcessManager;

    @GetMapping(value = "/classpath")
    public String processMessageFromClasspath(String message) {
        return messageProcessManager.processMessageFromClasspathFile(message);
    }

    @GetMapping(value = "/inline")
    public String processMessageFromInline(String message) {
        return messageProcessManager.processMessageFromInline(message);
    }

    @GetMapping(value = "/db")
    public String processMessageFromDB(String message) {
        return messageProcessManager.processMessageFromDB(message);
    }
}
