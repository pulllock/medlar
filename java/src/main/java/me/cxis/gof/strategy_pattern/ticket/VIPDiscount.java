package me.cxis.gof.strategy_pattern.ticket;

public class VIPDiscount implements Discount {

    @Override
    public double calculate(double price) {
        System.out.println("VIP ticket");
        System.out.println("增加积分");
        return price * 0.5;
    }
}
