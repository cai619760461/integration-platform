package com.incaier.integration.platform.request.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.incaier.integration.platform.annotation.ExcelValid;
import com.incaier.integration.platform.handler.excel.ExcelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 机构管理
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelMedicalEquipmentEntity {

    /**
     * 设备编号
     */
    @ColumnWidth(20)
    @ExcelProperty("设备编号")
    @ExcelValid(message = "设备编号必填")
    private String code;

    /**
     * 机构名称
     */
    @ColumnWidth(20)
    @ExcelProperty("设备名称")
    @ExcelValid(message = "设备名称必填")
    private String name;

    /**
     * 设备型号
     */
    @ColumnWidth(20)
    @ExcelProperty("设备型号")
    @ExcelValid(message = "设备型号必填")
    private String equipmentModel;

    /**
     * 生产厂家
     */
    @ColumnWidth(20)
    @ExcelProperty("生产厂家")
    @ExcelValid(message = "生产厂家必填")
    private String manufacturer;

    /**
     * 设备类别 id
     */
    @ColumnWidth(20)
    @ExcelProperty("设备类别 id")
    @ExcelValid(message = "设备类别 id 必填")
    private Integer equipmentTypeId;

    /**
     * 采购价格
     */
    @ColumnWidth(20)
    @ExcelProperty("采购价格")
    @ExcelValid(message = "采购价格 必填")
    private BigDecimal purchasePrice;

    /**
     * 供应商
     */
    @ColumnWidth(20)
    @ExcelProperty("供应商")
    @ExcelValid(message = "供应商 必填")
    private String supplier;

    /**
     * 合同编号
     */
    @ColumnWidth(20)
    @ExcelProperty("合同编号")
    @ExcelValid(message = "合同编号 必填")
    private String contractNumber;

    /**
     * 购置日期
     */
    @ColumnWidth(15)
    @ExcelProperty(value = "购置日期 yyyy-MM-dd", converter = ExcelConverter.LocalDateStringConverter.class)
    @ExcelValid(message = "购置日期 必填")
    private LocalDate purchaseDate;

    /**
     * 使用状态 0 正常使用 1 维修中 2 闲置
     */
    @ExcelValid(message = "使用状态 必填")
    @ColumnWidth(20)
    @ExcelProperty("使用状态 0 正常使用 1 维修中 2 闲置")
    private Integer status;

    /**
     * 资产归属部门
     */
    @ColumnWidth(20)
    @ExcelProperty("资产归属部门")
    @ExcelValid(message = "资产归属部门 必填")
    private String assetDepartment;

    /**
     * 资产责任人
     */
    @ColumnWidth(20)
    @ExcelProperty("资产责任人")
    private String assetResponsiblePerson;

    /**
     * 折旧信息
     */
    @ColumnWidth(20)
    @ExcelProperty("折旧信息")
    private String scrapInfo;
}


