package fun.pullock.cloud.open.feign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OkhttpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkhttpClientApplication.class, args);
    }
}
