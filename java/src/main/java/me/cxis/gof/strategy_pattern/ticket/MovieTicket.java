package me.cxis.gof.strategy_pattern.ticket;

public class MovieTicket {

    private double price;

    private Discount discount;

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return discount.calculate(price);
    }
}
