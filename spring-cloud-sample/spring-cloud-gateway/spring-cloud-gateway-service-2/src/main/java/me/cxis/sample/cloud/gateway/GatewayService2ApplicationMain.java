package me.cxis.sample.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayService2ApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayService2ApplicationMain.class, args);
    }
}
