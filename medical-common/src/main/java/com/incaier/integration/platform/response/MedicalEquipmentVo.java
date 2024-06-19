package com.incaier.integration.platform.response;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 医疗设备管理 vo
 *
 * @author weijie.cai
 * @description 医疗设备管理
 * @date 2024-06-04
 */
@Data
public class MedicalEquipmentVo implements Serializable {

    private static final long serialVersionUID = -3155406969101831430L;

    /**
     * id
     */
    @ExcelProperty("设备id")
    private Integer id;

    /**
     * 设备名称
     */
    @ExcelProperty("设备名称")
    private String name;

    /**
     * 设备编号
     */
    @ExcelProperty("设备编号")
    private String code;

    /**
     * 设备型号
     */
    @ExcelProperty("设备型号")
    private String equipmentModel;

    /**
     * 生产厂家
     */
    @ExcelProperty("生产厂家")
    private String manufacturer;

    /**
     * 设备图片文件路径
     */
    @ExcelIgnore
    private String equipmentImage;

    /**
     * 设备类别
     */
    @ExcelIgnore
    private Integer equipmentType;

    /**
     * 设备类别名称
     */
    @ExcelProperty("设备类别名称")
    private String equipmentTypeName;

    /**
     * 采购价格
     */
    @ExcelIgnore
    private BigDecimal purchasePrice;

    /**
     * 供应商
     */
    @ExcelIgnore
    private String supplier;

    /**
     * 合同编号
     */
    @ExcelIgnore
    private String contractNumber;

    /**
     * 购置日期
     */
    @ExcelProperty("购置日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date purchaseDate;

    /**
     * 使用状态 0 正常使用 1 维修中 2 闲置
     */
    @ExcelProperty("使用状态")
    private Integer status;

    /**
     * 资产归属部门
     */
    @ExcelIgnore
    private String assetDepartment;

    /**
     * 资产责任人
     */
    @ExcelIgnore
    private String assetResponsiblePerson;

    /**
     * 相关文档文件名
     */
    @ExcelIgnore
    private String relatedDocument;

    /**
     * 折旧信息
     */
    @ExcelIgnore
    private String scrapInfo;

    /**
     * 创建人
     */
    @ExcelIgnore
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @ExcelIgnore
    private String updateBy;

    /**
     * 更新时间
     */
    @ExcelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}