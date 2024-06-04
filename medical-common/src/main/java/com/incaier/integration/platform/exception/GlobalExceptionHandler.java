package com.incaier.integration.platform.exception;

import cn.hutool.core.util.StrUtil;
import com.incaier.integration.platform.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashSet;

/**
 * @Author Wangm
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        HashSet<String> errorList = new HashSet<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorList.add(fieldError.getField() + "参数:" + fieldError.getDefaultMessage());
            } else {
                errorList.add(error.getDefaultMessage());
            }
        }
        return Result.error(StrUtil.join(" ; ", errorList));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Result handleWebExchangeBindException(WebExchangeBindException e) {
        BindingResult bindingResult = e.getBindingResult();
        HashSet<String> errorList = new HashSet<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorList.add(fieldError.getField() + "参数:" + fieldError.getDefaultMessage());
            } else {
                errorList.add(error.getDefaultMessage());
            }
        }
        return Result.error(StrUtil.join(" ; ", errorList));
    }

    @ExceptionHandler(BindException.class)
    public Result<Object> handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        HashSet<String> errorList = new HashSet<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorList.add(fieldError.getField() + "参数:" + fieldError.getDefaultMessage());
            } else {
                errorList.add(error.getDefaultMessage());
            }
        }
        return Result.error(StrUtil.join(" ; ", errorList));
    }

    @ExceptionHandler(CommonBusinessException.class)
    public Result<Object> handleCommonBusinessException(CommonBusinessException e) {
//        e.printStackTrace();
        return Result.error(e.getCode(), e.getMessage());
    }

    // 全局异常拦截
    @ExceptionHandler
    public Result handlerException(Exception e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}
