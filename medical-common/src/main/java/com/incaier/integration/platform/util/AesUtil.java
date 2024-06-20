package com.incaier.integration.platform.util;

import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES 工具栏
 *
 * @author caiweijie
 * @date 2024/06/20
 */
public class AesUtil {
    private static final String ENCRYPTION_KEY = "YJZL@2022@GM57sN";
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return Base64.decodeBase64(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public static String encrypt(String content) {
        return base64Encode(aesEncryptToBytes(content, ENCRYPTION_KEY));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static String decrypt(String encryptStr) {
        return aesDecryptByBytes(base64Decode(encryptStr), ENCRYPTION_KEY);
    }

    public static String decryptDb(String encryptStr) {
        try {
            return aesDecryptByBytes(base64Decode(encryptStr), ENCRYPTION_KEY);
        } catch (Exception e) {
            return encryptStr;
        }
    }

//    public static void main(String[] args) {
//        String test = encrypt("YLZtdy@5tgb");
//        System.out.println(test);
//        System.out.println(decrypt("YnOmZOIRSIAOvd0C6/BRVw=="));
//    }
}