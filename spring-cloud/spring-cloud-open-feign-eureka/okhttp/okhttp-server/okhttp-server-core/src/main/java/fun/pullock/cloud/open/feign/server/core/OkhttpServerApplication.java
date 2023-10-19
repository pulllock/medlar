package fun.pullock.cloud.open.feign.server.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OkhttpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkhttpServerApplication.class, args);
    }
}
