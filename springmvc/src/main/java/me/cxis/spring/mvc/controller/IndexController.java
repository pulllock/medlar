package me.cxis.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

    @ResponseBody
    @RequestMapping("/info")
    public String info() {
        return "info_success";
    }
}
