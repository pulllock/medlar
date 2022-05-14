package me.cxis.groovy.controller;

import me.cxis.groovy.manager.BizManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/biz")
public class BizController {

    @Resource
    private BizManager bizManager;

    @GetMapping("/process")
    public void process() {
        bizManager.process();
    }
}
