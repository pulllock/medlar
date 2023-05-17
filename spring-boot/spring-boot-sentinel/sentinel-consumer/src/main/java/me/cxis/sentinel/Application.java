package me.cxis.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.setProperty("project.name", "sentinel-consumer");

        SpringApplication.run(Application.class, args);
    }
}
