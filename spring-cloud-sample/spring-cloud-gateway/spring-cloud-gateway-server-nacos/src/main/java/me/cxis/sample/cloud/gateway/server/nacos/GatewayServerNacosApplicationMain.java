package me.cxis.sample.cloud.gateway.server.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServerNacosApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerNacosApplicationMain.class, args);
    }
}
