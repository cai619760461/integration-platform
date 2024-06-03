package com.incaier.integration.platform.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 解密方法
 *
 * @author caiweijie
 * @date 2024/05/24
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(-2147483648)
@Inherited
public @interface DecryptMethod {
}
