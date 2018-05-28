package me.cxis.guice.motivation;

public interface CreditCardProcessor {

    ChargeResult charge(CreditCard creditCard, long amount);
}
