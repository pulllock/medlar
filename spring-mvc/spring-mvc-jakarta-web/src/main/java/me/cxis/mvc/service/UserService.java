package me.cxis.mvc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${app.name}")
    private String appName;

    public String getUserName(Long id) {
        return id + " xxx " + appName;
    }
}
