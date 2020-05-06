package me.cxis.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlaceHolderServiceImpl {

    @Value("${placeholder.user.prefix}")
    private String userPrefix;

    public String getUserPrefix() {
        return userPrefix;
    }
}
