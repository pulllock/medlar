package me.cxis.sample.nacos.client.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/client/index")
public class IndexController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @RequestMapping("/info")
    public String indexInfo() {
        return restTemplate.getForObject(userServiceUrl + "/user/queryNamePrefix", String.class);
    }
}
