package me.cxis.sample.cloud.client.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"me.cxis.sample.cloud.user.api.*"})
public class ClientApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplicationMain.class, args);
    }
}
