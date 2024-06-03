package com.incaier.integration.platform.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 加密方法
 *
 * @author caiweijie
 * @date 2024/05/24
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(-2147483648)
public @interface EncryptMethod {
}
