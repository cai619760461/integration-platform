package com.incaier.integration.platform.response.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 专家标签数据
 *
 * @author weijie.cai
 * @description 专家标签数据
 * @date 2024-06-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelVo implements Serializable {

    private static final long serialVersionUID = 7353385084499400121L;

    /**
    * 主键
    */
    private Integer id;

    /**
     * 字典id
     */
    private Integer dictId;

    /**
    * 字典标签
    */
    private String dictLabel;

    /**
    * 字典类型
    */
    private String dictType;
}