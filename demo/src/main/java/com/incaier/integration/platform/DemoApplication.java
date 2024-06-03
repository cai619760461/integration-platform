package com.incaier.integration.platform;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext appCtx;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Environment environment = appCtx.getBean(Environment.class);
        // 首先获取配置文件里的配置项
        String mysqlOriginPswd = environment.getProperty("spring.datasource.password");
        // 打印解密后的结果
        System.out.println("MySQL原始明文密码为：" + mysqlOriginPswd);
    }
}
