package me.cxis.spi.service.impl;

import me.cxis.test.spi.service.HelloWorldService;

/**
 * Created by cheng.xi on 2017-02-25 15:11.
 */
public class DefaultHelloWorldServiceImpl implements HelloWorldService {
    @Override
    public void sayHello() {
        System.out.println("default HelloWorldServiceImpl");
    }
}
