package com.incaier.integration.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Excel导入必填校验注解</p>
 *
 * @author caiweijie
 * @date 2024/06/17
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelValid {

    String message() default "导入有未填入的字段";

}