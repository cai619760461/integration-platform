package com.incaier.integration.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"com.incaier.integration.platform.mapper"})
public class MedicalSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalSystemApplication.class, args);
    }
}