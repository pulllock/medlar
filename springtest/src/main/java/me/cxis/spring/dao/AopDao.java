package me.cxis.spring.dao;

import org.springframework.stereotype.Component;

@Component
public class AopDao {

    public String getSomething() {
        return "something";
    }
}
