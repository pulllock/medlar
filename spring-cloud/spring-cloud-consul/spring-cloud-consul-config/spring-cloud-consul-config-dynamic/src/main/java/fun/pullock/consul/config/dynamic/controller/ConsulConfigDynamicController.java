package fun.pullock.consul.config.dynamic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consul/config/dynamic")
public class ConsulConfigDynamicController {

    @GetMapping("/query")
    public String query() {
        return "result from consul config server";
    }
}
