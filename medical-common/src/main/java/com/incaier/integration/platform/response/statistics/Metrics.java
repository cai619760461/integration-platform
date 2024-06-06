package com.incaier.integration.platform.response.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * 月份指标
 *
 * @author caiweijie
 * @date 2024/06/05
 */
@Data
public class Metrics implements Serializable {

    private static final long serialVersionUID = -3117411166405419509L;

    private String name;

    private Integer count;
}
