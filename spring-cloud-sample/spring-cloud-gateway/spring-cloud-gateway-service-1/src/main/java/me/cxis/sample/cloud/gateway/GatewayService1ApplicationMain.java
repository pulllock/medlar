package me.cxis.sample.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayService1ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayService1ApplicationMain.class, args);
    }
}
