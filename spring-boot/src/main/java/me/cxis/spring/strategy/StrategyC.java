package me.cxis.spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class StrategyC implements BaseStrategy {

    @Override
    public String doSomething(String param) {
        return "strategy c";
    }
}
