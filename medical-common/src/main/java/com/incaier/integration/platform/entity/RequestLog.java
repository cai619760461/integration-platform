package com.incaier.integration.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问日志
 *
 * @author caiweijie
 * @date 2024/06/25
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestLog {

    /**
     * 请求id
     */
    private String requestId;

    /**
     * token
     */
    private String token;

    /**
     * ip
     */
    private String ip;

    /**
     * uri
     */
    private String uri;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 查询参数
     */
    private String queryParam;

    /**
     * 查询参数
     */
    private String queryBody;
}
