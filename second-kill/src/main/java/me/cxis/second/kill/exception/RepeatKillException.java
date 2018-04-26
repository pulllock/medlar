package me.cxis.second.kill.exception;

public class RepeatKillException extends SecondKillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
