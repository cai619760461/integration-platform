package com.incaier.integration.platform.exception;

/**
 * 负载过大异常
 *
 * @author caiweijie
 * @date 2024/06/25
 */
public class PayloadTooLargeException extends RuntimeException {

    private static final long serialVersionUID = 3273651429076015456L;

    public PayloadTooLargeException(int maxBodySize) {
        super();
    }
}