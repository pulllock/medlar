package me.cxis.spi;


import me.cxis.spi.service.HelloWorldService;

import java.util.ServiceLoader;

/**
 * Created by cheng.xi on 2017-02-25 15:16.
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<HelloWorldService> helloWorldServices = ServiceLoader.load(HelloWorldService.class);
        for(HelloWorldService helloWorldService:helloWorldServices){
            helloWorldService.sayHello();
        }
    }

}
