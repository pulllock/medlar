package me.cxis.consul.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consul/discovery/server")
public class ConsulDiscoveryServerController {

    @GetMapping("/query")
    public String query() {
        return "result from consul discovery server";
    }
}
