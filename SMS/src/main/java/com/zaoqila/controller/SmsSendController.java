package com.zaoqila.controller;

import com.zaoqila.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by justdoit on 15-5-27.
 */
@Controller
public class SmsSendController {

    @Autowired
    private SendSmsService sendSmsService;

    @RequestMapping("/sendSms")
    @ResponseBody
    public String sendSms(HttpServletRequest request){
        System.out.println("Start send SMS......");
        String phoneNumber = request.getParameter("phoneNumber");
        String smsText = request.getParameter("smsText");
        System.out.println("phoneNumber:" + phoneNumber + ";smsText:" + smsText + "qwqqwq");
        sendSmsService.sendSms();
        return "";
    }
}
