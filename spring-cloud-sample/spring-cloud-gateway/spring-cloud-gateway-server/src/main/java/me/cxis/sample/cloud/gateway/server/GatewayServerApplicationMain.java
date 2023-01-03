package me.cxis.sample.cloud.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServerApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplicationMain.class, args);
    }
}
