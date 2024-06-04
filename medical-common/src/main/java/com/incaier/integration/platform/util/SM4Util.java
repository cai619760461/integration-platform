package com.incaier.integration.platform.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

/**
 * sm4 util
 *
 * @author caiweijie
 * @Description sm4加密算法工具类
 * @date 2024/05/24
 */
public class SM4Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;
    // 默认密钥
    public static final String DEFAULT_KEY = "bd3cd8db3a5b6041b8da9adda583634e";

    /**
     * 生成ECB暗号
     *
     * @param algorithmName 算法名称
     * @param mode          模式
     * @param key           钥匙
     * @return {@link Cipher}
     * @throws Exception 例外
     * @explain ECB模式（电子密码本模式：Electronic codebook）
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * 自动生成密钥
     *
     * @return {@link String}
     * @throws Exception 例外
     * @explain
     */
    public static String generateKey() throws Exception {
        return new String(Hex.encode(generateKey(DEFAULT_KEY_SIZE)));
    }

    /**
     * 生成密钥
     *
     * @param keySize 密钥大小
     * @return {@link byte[]}
     * @throws Exception 例外
     * @explain
     */
    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /**
     * sm4加密
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @param charset  字符集
     * @return 返回16进制的加密字符串
     * @throws Exception 例外
     * @explain 加密模式：ECB
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public static String encryptEcb(String hexKey, String paramStr, String charset) throws Exception {
        String cipherText = "";
        if (null != paramStr && !paramStr.isEmpty()) {
            // 16进制字符串-->byte[]
            byte[] keyData = ByteUtils.fromHexString(hexKey);
            charset = charset.trim();
            if (charset.length() <= 0) {
                charset = ENCODING;
            }
            // String-->byte[]
            byte[] srcData = paramStr.getBytes(charset);
            // 加密后的数组
            byte[] cipherArray = encryptEcbPadding(keyData, srcData);
            // byte[]-->hexString
            cipherText = ByteUtils.toHexString(cipherArray);
        }
        return cipherText;
    }

    /**
     * 加密模式之Ecb
     *
     * @param key  钥匙
     * @param data 数据
     * @return {@link byte[]}
     * @throws Exception 例外
     * @explain
     */
    public static byte[] encryptEcbPadding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * sm4 解密
     *
     * @param hexKey     16进制密钥
     * @param cipherText 16进制的加密字符串（忽略大小写）
     * @param charset    字符集
     * @return 解密后的字符串
     * @throws Exception 例外
     * @explain 解密模式：采用ECB
     */
    public static String decryptEcb(String hexKey, String cipherText, String charset) throws Exception {
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decryptEcbPadding(keyData, cipherData);
        // byte[]-->String
        charset = charset.trim();
        if (charset.length() <= 0) {
            charset = ENCODING;
        }
        decryptStr = new String(srcData, charset);
        return decryptStr;
    }

    /**
     * 解密
     *
     * @param key        钥匙
     * @param cipherText 密文
     * @return {@link byte[]}
     * @throws Exception 例外
     */
    public static byte[] decryptEcbPadding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    /**
     * 校验加密前后的字符串是否为同一数据
     *
     * @param hexKey     16进制密钥（忽略大小写）
     * @param cipherText 16进制加密后的字符串
     * @param paramStr   加密前的字符串
     * @return 是否为同一数据
     * @throws Exception 例外
     */
    public static boolean verifyEcb(String hexKey, String cipherText, String paramStr) throws Exception {
        // 用于接收校验结果
        boolean flag = false;
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // 将16进制字符串转换成数组
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] decryptData = decryptEcbPadding(keyData, cipherData);
        // 将原字符串转换成byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 判断2个数组是否一致
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }

    /**
     * @Description:测试类
     */
    public static void main(String[] args) {
        try {
//            String json = "{\"name\":\"caiweijie\",\"sex\":\"man\"}";
            String json = "caiweijie";
            // 自定义的32位16进制密钥
            String key = "bd3cd8db3a5b6041b8da9adda583634e";
//            String cipher = SM4Util.encryptEcb(key, json, ENCODING);
//            System.out.println(cipher);
//            System.out.println(SM4Util.verifyEcb(key, cipher, json));
            json = SM4Util.decryptEcb(key, "9f5ba7f0f63eb1b64a3653ee0376a026", ENCODING);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
