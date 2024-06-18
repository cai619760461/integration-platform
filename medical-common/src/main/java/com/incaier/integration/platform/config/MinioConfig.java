package com.incaier.integration.platform.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * description: 获取配置文件信息
 *
 * @author caiweijie
 * @date 2024/06/18
 */
@Configuration
@EnableConfigurationProperties(MinioPropertiesConfig.class)
public class MinioConfig {

    @Resource
    private MinioPropertiesConfig minioPropertiesConfig;

    /**
     * 初始化 MinIO 客户端
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioPropertiesConfig.getEndpoint())
                .credentials(minioPropertiesConfig.getAccessKey(), minioPropertiesConfig.getSecretKey())
                .build();
    }
}