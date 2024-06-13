package com.incaier.integration.platform.response.org;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.incaier.integration.platform.config.ExcelConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 机构信息表
 *
 * @description 机构信息表
 * @author weijie.cai
 * @date 2024-06-13
 */
@Data
public class OrgVo implements Serializable {

    private static final long serialVersionUID = -1916503588420002626L;

    /**
    * 主键id
    */
    @ExcelProperty("id序号")
    private Long id;

    /**
    * 机构id
    */
    @ExcelProperty("机构id")
    private String code;

    /**
    * 机构序号
    */
    @ExcelIgnore
    private Integer position;

    /**
    * 机构类型编码
    */
    @ExcelProperty("机构类型编码")
    private String typeCode;

    /**
    * 机构类型名称
    */
    @ExcelProperty("机构类型名称")
    private String typeName;

    /**
    * 机构名称
    */
    @ExcelProperty("机构名称")
    private String name;

    /**
    * 机构联系人
    */
    @ExcelProperty("机构联系人")
    private String contactName;

    /**
    * 机构联系人职位
    */
    @ExcelProperty("机构联系人职位")
    private String contactTitle;

    /**
    * 机构联系人电话
    */
    @ExcelProperty("机构联系人电话")
    private String contactPhone;

    /**
    * 机构所属区域
    */
    @ExcelProperty("机构所属区域")
    private String district;

    /**
    * 机构所属县镇
    */
    @ExcelProperty("机构所属县镇")
    private String county;

    /**
    * 机构地址
    */
    @ExcelProperty("机构地址")
    private String address;

    /**
    * 备注
    */
    @ExcelProperty("备注")
    private String remark;

    /**
    * 是否启用 1启用/0未启用
    */
    @ExcelProperty(value = "是否启用", converter = ExcelConverter.EnableConverter.class)
    private Integer enabled;

    /**
    * 创建时间
    */
    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
    * 修改时间
    */
    @ExcelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}