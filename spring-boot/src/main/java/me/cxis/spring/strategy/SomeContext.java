package me.cxis.spring.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class SomeContext {

    @Resource
    private Map<String, BaseStrategy> strategies;

    public BaseStrategy getStrategy(String name) {
        return strategies.get(name);
    }
}
