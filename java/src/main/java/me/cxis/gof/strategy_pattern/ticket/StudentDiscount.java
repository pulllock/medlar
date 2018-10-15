package me.cxis.gof.strategy_pattern.ticket;

public class StudentDiscount implements Discount{

    @Override
    public double calculate(double price) {
        System.out.println("Student ticket");
        return price * 0.8;
    }
}
