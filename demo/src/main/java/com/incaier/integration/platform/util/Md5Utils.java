package com.incaier.integration.platform.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 功能描述:PICC加密方式
 *
 * @author: hzw
 * @date: 2021-08-10 17:13
 */
public class Md5Utils {

    /**
     * md5 加密
     *
     * @param inStr 在str中
     * @return {@link String}
     */
    public static String encodeMd5(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = md5Byte & 255;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString().toUpperCase();
        } catch (Exception var7) {
            return null;
        }
    }

    /**
     * 用secret编码md5
     *
     * @param inStr  在str中
     * @param secret 秘密
     * @return {@link String}
     */
    public static String encodeMd5WithSecret(String inStr, String secret) {
        return encodeMd5(inStr + secret);
    }

    /**
     * 用户登录接口密码MD5加密方式
     *
     * @param plainText 明文
     * @return 32位密文
     */
    public static String encryption(String plainText) {
        String reMd5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte value : b) {
                i = value;
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            reMd5 = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reMd5;
    }
}
