package me.cxis.cloud.gateway.server.exception;

public enum ErrorCode {

    /** 错误码 **/
    SUCCESS(0, "成功"),
    SYSTEM_ERROR(500, "系统错误"),

    UNAUTHORIZED(401, "没有访问权限"),
    TOO_MANY_REQUESTS(429, "访问受限")
    ;

    private int code;

    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
