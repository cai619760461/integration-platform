package com.incaier.integration.platform.aop;

import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.incaier.integration.platform.annotation.EncryptField;
import com.incaier.integration.platform.util.Md5Utils;
import com.incaier.integration.platform.util.SM4Util;
import com.incaier.integration.platform.util.SpringContextUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * sm4方面
 *
 * @author caiweijie
 * @Description:sm4加密解密切面
 * @date 2024/05/24
 */
@Order(-2147483648)
@Aspect
@Component
@ConditionalOnProperty(prefix = "sm4", value = "enable", matchIfMissing = false)
public class Sm4Aspect {

    private final Logger log = LoggerFactory.getLogger(Sm4Aspect.class);

    private static final String DEFAULT_KEY = "bd3cd8db3a5b6041b8da9adda583634e";

    public Sm4Aspect() {
    }

    @Pointcut("@annotation(com.example.demo.annotation.EncryptMethod)")
    public void encryptAopCut() {
    }

    @Pointcut("@annotation(com.example.demo.annotation.DecryptMethod)")
    public void decryptAopCut() {
    }

    @Around("encryptAopCut()")
    public Object encryptMethodAop(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            responseObj = joinPoint.proceed();
            this.handleEncrypt(responseObj);
            //md5加密
            String md5Data = Md5Utils.encodeMd5(new Gson().toJson(responseObj));
            SpringContextUtils.getHttpServletResponse().setHeader("md5", md5Data);
        } catch (Throwable throwable) {
            log.error("encryptMethodAop 处理出现异常", throwable);
        }
        return responseObj;
    }

    @Around("decryptAopCut()")
    public Object decryptMethodAop(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            responseObj = joinPoint.getArgs()[0];
            //throw new RuntimeException("md5校验失败");
            this.handleDecrypt(responseObj);
            String md5 = "";
            md5 = Md5Utils.encodeMd5(new Gson().toJson(responseObj));
            log.info(md5);
            String origianlMd5 = "";
            origianlMd5 = SpringContextUtils.getHttpServletRequest().getHeader("md5");
            if (origianlMd5.equals(md5)) {
                responseObj = joinPoint.proceed();
            } else {
                log.error("参数的md5校验不同，可能存在篡改行为，请检查！");
                throw new Exception("参数的md5校验不同，可能存在篡改行为，请检查！");
            }
        } catch (Throwable throwable) {
            log.error("decryptMethodAop 处理出现异常", throwable);
        }
        return responseObj;
    }

    private void handleEncrypt(Object requestObj) throws Exception {
        if (!Objects.isNull(requestObj)) {
            Field[] fields = requestObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
                if (hasSecureField) {
                    field.setAccessible(true);
                    String plaintextValue = (String) field.get(requestObj);
                    String encryptValue = SM4Util.encryptEcb(DEFAULT_KEY, plaintextValue, "");
                    field.set(requestObj, encryptValue);
                }
            }
        }
    }

    private void handleDecrypt(Object responseObj) throws Exception {
        if (!Objects.isNull(responseObj)) {
            Field[] fields = responseObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
                if (hasSecureField) {
                    field.setAccessible(true);
                    String encryptValue = (String) field.get(responseObj);
                    String plaintextValue = SM4Util.decryptEcb(DEFAULT_KEY, encryptValue, "");
                    field.set(responseObj, plaintextValue);
                }
            }
        }
    }
}

