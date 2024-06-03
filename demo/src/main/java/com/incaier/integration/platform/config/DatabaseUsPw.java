package com.incaier.integration.platform.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author caiweijie
 * @link <a href="https://zhuanlan.zhihu.com/p/136138588">...</a>
 * @date 2024/04/19
 */
@Configuration
public class DatabaseUsPw {

    /**
     * 字符串加密器
     *
     * @return {@link StringEncryptor}
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        /**
         * password 方式
         * 方式一：直接作为程序启动时的命令行参数来带入
         * java -jar yourproject.jar --jasypt.encryptor.password=CodeSheep
         *
         * 方式二：直接作为程序启动时的应用环境变量来带入
         * java -Djasypt.encryptor.password=CodeSheep -jar yourproject.jar
         *
         * 方式三：甚至可以作为系统环境变量的方式来带入
         * 比方说，我们提前设置好系统环境变量JASYPT_ENCRYPTOR_PASSWORD = CodeSheep，则直接在Spring Boot的项目配置文件中做如下配置即可：
         * jasypt.encryptor.password=${JASYPT_ENCRYPTOR_PASSWORD:}
         */
        config.setPassword("caiweijietest");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    public static void main(String[] args) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        /**
         * password 方式
         * 方式一：直接作为程序启动时的命令行参数来带入
         * java -jar yourproject.jar --jasypt.encryptor.password=CodeSheep
         *
         * 方式二：直接作为程序启动时的应用环境变量来带入
         * java -Djasypt.encryptor.password=CodeSheep -jar yourproject.jar
         *
         * 方式三：甚至可以作为系统环境变量的方式来带入
         * 比方说，我们提前设置好系统环境变量JASYPT_ENCRYPTOR_PASSWORD = CodeSheep，则直接在Spring Boot的项目配置文件中做如下配置即可：
         * jasypt.encryptor.password=${JASYPT_ENCRYPTOR_PASSWORD:}
         */
        config.setPassword("caiweijietest");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        PooledPBEStringEncryptor textEncryptor = new PooledPBEStringEncryptor();
        textEncryptor.setConfig(config);
        //加密所需的salt(盐)
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("root");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        //解密方法
        System.out.println("username:" + textEncryptor.decrypt(username));
        System.out.println("password:" + textEncryptor.decrypt(password));
    }
}
