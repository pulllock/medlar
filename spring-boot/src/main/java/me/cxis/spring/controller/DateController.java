package me.cxis.spring.controller;

import com.alibaba.fastjson.JSON;
import me.cxis.spring.model.DateReq;
import me.cxis.spring.model.DateResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/date")
public class DateController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateController.class);

    @PostMapping("/post")
    public DateResp postDate(@RequestBody DateReq req) {
        LOGGER.info("post date req: {}", req.getStartTime());

        DateResp resp = new DateResp();
        resp.setStartTime(req.getStartTime());
        return resp;
    }
}
