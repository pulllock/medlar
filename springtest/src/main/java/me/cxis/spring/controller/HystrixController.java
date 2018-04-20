package me.cxis.spring.controller;

import me.cxis.spring.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hystrix")
public class HystrixController {

    @Autowired
    private HystrixService hystrixService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return hystrixService.hello();
    }

    @RequestMapping(value = "/threadpool", method = RequestMethod.GET)
    public String hystrixThreadPool() {
        return hystrixService.hystrixThreadPool();
    }

    @RequestMapping(value = "/semaphore", method = RequestMethod.GET)
    public String hystrixSemaphreo() {
        return hystrixService.hystrixSemaphreo();
    }
}
