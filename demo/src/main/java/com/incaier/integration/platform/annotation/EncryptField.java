package com.incaier.integration.platform.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 加密字段
 *
 * @author caiweijie
 * @date 2024/05/24
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(-2147483648)
public @interface EncryptField {
}
