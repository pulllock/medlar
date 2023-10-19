package fun.pullock.lock.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLockRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLockRedisApplication.class, args);
    }

}
