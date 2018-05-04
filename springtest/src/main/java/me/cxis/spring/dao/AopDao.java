package me.cxis.spring.dao;

import org.springframework.stereotype.Component;

@Component
public class AopDao {

    public String getSomething() {
        return "something";
    }

    public String getSomething1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "something1";
    }
}
