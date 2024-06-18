package com.incaier.integration.platform.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description: minio配置类
 *
 * @author caiweijie
 * @date 2024/06/18
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioPropertiesConfig {

    /**
     * 可用，必须 true
     */
    private Boolean enable;

    /**
     * https
     */
    private Boolean secure;

    /**
     * 端点
     */
    private String endpoint;

    /**
     * 用户名
     */
    private String accessKey;

    /**
     * 密码
     */
    private String secretKey;

    /**
     * 桶名称
     */
    private String bucketName;
}