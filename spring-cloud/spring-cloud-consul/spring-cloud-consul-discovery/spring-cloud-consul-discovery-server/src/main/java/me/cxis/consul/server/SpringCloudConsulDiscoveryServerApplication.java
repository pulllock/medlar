package me.cxis.consul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudConsulDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulDiscoveryServerApplication.class, args);
    }

}
