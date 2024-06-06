package com.incaier.integration.platform.exception;

import lombok.Getter;

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
    @Getter
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
}
