package me.cxis.externalized.configuration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external/config")
public class ExternalizedConfigurationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExternalizedConfigurationController.class);

    @Value("${app.name}")
    private String appName;

    @GetMapping("/get")
    public String get() {
        LOGGER.info("app name: {}", appName);
        return appName;
    }
}
