package com.incaier.integration.platform.response.doctor;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 专家信息数据
 *
 * @author weijie.cai
 * @description 专家信息数据
 * @date 2024-06-12
 */
@Data
public class ExpertVo implements Serializable {

    private static final long serialVersionUID = 2084236157978331999L;

    /**
    * 是否专家
    */
    private Integer isExpert;

    /**
     * 专家标签
     */
    private List<LabelVo> expertLabels;
}