package fun.pullock.nacos.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos/discovery/server")
public class NacosDiscoveryServerController {

    @GetMapping("/query")
    public String query() {
        return "result from nacos discovery server";
    }
}
