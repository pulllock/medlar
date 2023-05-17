package me.cxis.cloud.gateway.server.exception;

public class GatewayException extends RuntimeException {

    ErrorCode errorCode = ErrorCode.SYSTEM_ERROR;

    public GatewayException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
