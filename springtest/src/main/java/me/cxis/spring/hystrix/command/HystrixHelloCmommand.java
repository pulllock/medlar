package me.cxis.spring.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HystrixHelloCmommand extends HystrixCommand<String> {

    private final String name;

    public HystrixHelloCmommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HystrixGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello: " + name;
    }


}
