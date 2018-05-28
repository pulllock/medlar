package me.cxis.guice.motivation;

public interface TransactionLog {

    void logChargeResult(ChargeResult result);

    void logConnectException(UnreachableException e);
}
