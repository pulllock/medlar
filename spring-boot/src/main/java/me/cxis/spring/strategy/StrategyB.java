package me.cxis.spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class StrategyB implements BaseStrategy {

    @Override
    public String doSomething(String param) {
        return "strategy b";
    }
}
