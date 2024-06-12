package com.incaier.integration.platform.response.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典数据表
 *
 * @author weijie.cai
 * @description 字典数据表
 * @date 2024-06-12
 */
@Data
public class SysDictDataVo implements Serializable {

    private static final long serialVersionUID = 5969259740768755038L;

    /**
    * 字典主键
    */
    private Integer id;

    /**
    * 字典排序
    */
    private Integer dictSort;

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
    * 样式属性（其他样式扩展）
    */
    private String cssClass;

    /**
    * 表格回显样式
    */
    private String listClass;

    /**
    * 是否默认（1是 0否）
    */
    private Integer isDefault;

    /**
    * 状态（0正常 1停用）
    */
    private Integer status;

    /**
    * 创建者
    */
    private String createBy;

    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
    * 更新者
    */
    private String updateBy;

    /**
    * 更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
    * 备注
    */
    private String remark;
}