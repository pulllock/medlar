package me.cxis.guice.motivation;

public class Receipt {

    public static Receipt forSuccessfulCharge(long amount) {
        return null;
    }

    public static Receipt forDeclinedCharge(Object declineMessage) {
        return null;
    }

    public static Receipt forSystemFailure(String message) {
        return null;
    }

    public boolean hasSuccessfulCharge() {
        return false;
    }
}
