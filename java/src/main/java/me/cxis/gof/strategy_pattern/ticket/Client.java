package me.cxis.gof.strategy_pattern.ticket;

public class Client {

    public static void main(String[] args) {
        MovieTicket ticket = new MovieTicket();
        double originPrice = 60.0;
        double currentPrice;

        ticket.setPrice(originPrice);

        Discount discount = new StudentDiscount(); // 可以使用xml配置文件
        ticket.setDiscount(discount);

        currentPrice = ticket.getPrice();
        System.out.println(currentPrice);
    }
}
