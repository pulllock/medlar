package fun.pullock.consul.client.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/consul/discovery/client")
public class ConsulDiscoveryClientController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service.spring-cloud-consul-discovery-server}")
    private String serverUrl;

    @GetMapping("/query")
    public String query() {
        return restTemplate.getForObject("http://" + serverUrl + "/consul/discovery/server/query", String.class);
    }
}
