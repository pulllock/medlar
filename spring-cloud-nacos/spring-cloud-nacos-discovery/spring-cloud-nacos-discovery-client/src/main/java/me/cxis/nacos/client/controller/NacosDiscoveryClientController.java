package me.cxis.nacos.client.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/nacos/discovery/client")
public class NacosDiscoveryClientController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service.spring-cloud-nacos-discovery-server}")
    private String serverUrl;

    @GetMapping("/query")
    public String query() {
        return restTemplate.getForObject("http://" + serverUrl + "/nacos/discovery/server/query", String.class);
    }
}
