package me.cxis.guice.motivation;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestClient {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BillingModule());

        BillingService billingService = injector.getInstance(BillingService.class);
    }
}
