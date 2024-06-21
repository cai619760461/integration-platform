package com.incaier.integration.platform.response.dict;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据表
 *
 * @author weijie.cai
 * @description 字典数据表
 * @date 2024-06-12
 */
@Data
public class SysDictSimpleVo implements Serializable {

    private static final long serialVersionUID = 3283868763043094427L;

    /**
    * 字典主键
    */
    private Integer id;

    /**
    * 字典标签
    */
    private String dictLabel;

    /**
    * 字典键值
    */
    private String dictValue;

    /**
    * 字典类型
    */
    private String dictType;

    /**
    * 是否默认（1是 0否）
    */
    private Integer isDefault;

    /**
    * 状态（0正常 1停用）
    */
    private Integer status;
}