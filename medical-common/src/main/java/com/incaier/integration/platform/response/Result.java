package com.incaier.integration.platform.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Author Wangm
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 3359415549885982623L;
    private int code;
    private String message;
    private boolean success;
    private boolean check;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result();
        result.setCode(CodeMessage.SYSTEM_SUCCESS.getCode());
        result.setMessage(CodeMessage.SYSTEM_SUCCESS.getMessage());
        result.setSuccess(true);
        result.setCheck(true);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result();
        result.setCode(CodeMessage.SYSTEM_SUCCESS.getCode());
        result.setMessage(CodeMessage.SYSTEM_SUCCESS.getMessage());
        result.setSuccess(true);
        result.setCheck(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(boolean isCheck,String message) {
        Result<T> result = new Result();
        result.setCode(CodeMessage.SYSTEM_SUCCESS.getCode());
        result.setMessage(message);
        result.setCheck(isCheck);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result();
        result.setCode(CodeMessage.SYSTEM_SUCCESS.getCode());
        result.setMessage(message);
        result.setSuccess(true);
        result.setCheck(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data, Integer total) {
        Result<T> result = new Result();
        result.setCode(CodeMessage.SYSTEM_SUCCESS.getCode());
        result.setMessage(CodeMessage.SYSTEM_SUCCESS.getMessage());
        result.setSuccess(true);
        result.setTotal(total);
        result.setData(data);
        result.setCheck(true);
        return result;
    }


    public static <T> Result<T> error() {
        Result<T> result = new Result();
        result.setCode(CodeMessage.SYSTEM_ERROR.getCode());
        result.setMessage(CodeMessage.SYSTEM_ERROR.getMessage());
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> error(int code, String message, T data) {
        Result<T> result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(T data) {
        Result<T> result = new Result();
        result.setCode(CodeMessage.SYSTEM_ERROR.getCode());
        result.setMessage(CodeMessage.SYSTEM_ERROR.getMessage());
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", isCheck=" + check +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public Result() {
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}