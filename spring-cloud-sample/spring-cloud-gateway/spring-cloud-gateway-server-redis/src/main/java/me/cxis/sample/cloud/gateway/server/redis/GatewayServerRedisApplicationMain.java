package me.cxis.sample.cloud.gateway.server.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServerRedisApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerRedisApplicationMain.class, args);
    }
}
