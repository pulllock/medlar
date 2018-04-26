package me.cxis.second.kill.exception;

public class SecondKillException extends RuntimeException {

    public SecondKillException(String message) {
        super(message);
    }

    public SecondKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
