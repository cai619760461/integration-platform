package com.incaier.integration.platform.exception;

/**
 * 自定义异常
 *
 * @author caiweijie
 * @date 2024/04/18
 */
public class CommonBusinessException extends RuntimeException {

    /**
     * 返回 code
     */
    private Integer code;

    /**
     * 返回 message
     */
    private String message;

    public CommonBusinessException(String message) {
        super(message);
    }

    public CommonBusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
