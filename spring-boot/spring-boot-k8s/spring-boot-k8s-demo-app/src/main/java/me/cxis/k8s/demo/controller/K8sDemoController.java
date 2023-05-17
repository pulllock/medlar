package me.cxis.k8s.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/k8s/demo")
public class K8sDemoController {

    @GetMapping("/query")
    public String query() throws UnknownHostException {
        return "hello k8s demo! From server: " + InetAddress.getLocalHost();
    }
}
