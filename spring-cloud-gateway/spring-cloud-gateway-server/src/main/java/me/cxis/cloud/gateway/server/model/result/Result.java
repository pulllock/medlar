package me.cxis.cloud.gateway.server.model.result;

import me.cxis.cloud.gateway.server.exception.ErrorCode;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6007707803562337863L;

    private int code;

    private String message;

    private T data;

    private boolean success;

    public Result(T data) {
        this.data = data;
        this.code = 0;
        this.message = null;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public Result(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
        this.data = null;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
