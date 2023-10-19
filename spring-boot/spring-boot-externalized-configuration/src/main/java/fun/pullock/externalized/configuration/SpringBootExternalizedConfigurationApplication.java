package fun.pullock.externalized.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootExternalizedConfigurationApplication {

    public static void main(String[] args) {
        System.setProperty("app.name", "app name from system property");
        SpringApplication.run(SpringBootExternalizedConfigurationApplication.class, args);
    }
}
