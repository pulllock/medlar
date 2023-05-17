package me.cxis.gitlab.k8s.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitlabK8sHealthController {

    @GetMapping("/alive")
    public String alive() {
        return "alive";
    }
}
