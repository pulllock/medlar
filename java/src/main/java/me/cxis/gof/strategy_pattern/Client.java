package me.cxis.gof.strategy_pattern;

public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        AbstractStrategy strategy = new ConcreteStrategyA();
        context.setStrategy(strategy);
        context.algorithm();
    }
}
