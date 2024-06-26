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
public class ResponseLog {

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 请求 code
     */
    private Integer status;

    /**
     * 耗时
     */
    private long ms;

    /**
     * 响应
     */
    private String responseBody;
}
