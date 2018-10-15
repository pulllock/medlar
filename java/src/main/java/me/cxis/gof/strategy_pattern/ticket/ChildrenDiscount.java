package me.cxis.gof.strategy_pattern.ticket;

public class ChildrenDiscount implements Discount{

    @Override
    public double calculate(double price) {
        System.out.println("Children ticket");
        return price - 10;
    }
}
