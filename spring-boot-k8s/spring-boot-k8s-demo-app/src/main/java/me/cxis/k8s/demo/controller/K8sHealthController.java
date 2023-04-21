package me.cxis.k8s.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class K8sHealthController {

    @GetMapping("/alive")
    public String alive() {
        return "alive";
    }
}
