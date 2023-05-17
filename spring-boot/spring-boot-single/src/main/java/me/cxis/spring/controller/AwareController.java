package me.cxis.spring.controller;

import me.cxis.spring.aware.SpringApplicationContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aware")
public class AwareController {

    @GetMapping("/bean/get/")
    public void getBean() {
        SpringApplicationContextUtil.getBean("xxxx");
    }
}
