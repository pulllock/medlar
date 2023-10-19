package fun.pullock.docker.compose.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static String HOST_PORT = "http://spring-boot-docker-compose-server:8081";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/queryById")
    public String queryById(@RequestParam Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("参数错误");
        }

        ResponseEntity<String> response = restTemplate.getForEntity(HOST_PORT + "/user/queryById?id=1", String.class);
        return response.getBody();
    }
}
