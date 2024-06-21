package com.incaier.integration.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 医疗系统应用
 *
 * @author caiweijie
 * @date 2024/06/21
 */
@SpringBootApplication
@EnableCaching
@MapperScan({"com.incaier.integration.platform.mapper"})
public class MedicalSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalSystemApplication.class, args);
    }
}