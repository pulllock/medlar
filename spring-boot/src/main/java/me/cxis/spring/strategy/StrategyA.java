package me.cxis.spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class StrategyA implements BaseStrategy {

    @Override
    public String doSomething(String param) {
        return "strategy a";
    }
}
