package me.cxis.controller;

import me.cxis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by justdoit on 15-6-2.
 */
@Controller
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/sendSms")
    public void sendSms(HttpServletRequest request){
        System.out.println("start send sms");
        redisService.sendSms();
    }
}
