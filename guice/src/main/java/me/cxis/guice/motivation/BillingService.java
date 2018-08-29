package me.cxis.guice.motivation;

public interface BillingService {

    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
