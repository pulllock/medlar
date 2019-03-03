package me.cxis.spring.controller;

import me.cxis.spring.service.AsyncService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @RequestMapping(value = "/testAsync", method = RequestMethod.GET)
    public String testAsync() {
        asyncService.testAsync();
        return "sucess";
    }

    @RequestMapping(value = "/testAsync1", method = RequestMethod.GET)
    public String testAsync1() {
        asyncService.testAsync1();
        return "sucess";
    }
}
