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

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

/**
 * 全局异常处理程序
 *
 * @author caiweijie
 * @date 2024/06/25
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理方法参数无效异常
     *
     * @param e e
     * @return {@link Result}<{@link String}>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashSet<String> errorList = new HashSet<>();
        e.getBindingResult().getAllErrors().forEach(error -> errorList.add(error.getDefaultMessage()));
        return Result.error(StrUtil.join(" ; ", errorList));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Result<String> handleWebExchangeBindException(WebExchangeBindException e) {
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
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    // 全局异常拦截
    @ExceptionHandler
    public Result<String> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    /**
     * 绑定异常处理程序,处理 RequestParam + validate
     *
     * @param ex 前-
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<String> bindExceptionHandler(ConstraintViolationException ex) {
        HashSet<String> errorList = new HashSet<>();
        ex.getConstraintViolations().forEach(x -> errorList.add(x.getMessage()));
        return Result.error(StrUtil.join(" ; ", errorList));
    }

    /**
     * 绑定异常处理程序,处理 RequestParam + validate
     *
     * @param ex 前-
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler({PayloadTooLargeException.class})
    public Result<String> PayloadTooLargeExceptionHandler(PayloadTooLargeException ex) {
        return Result.error("请求体过大");
    }
}
