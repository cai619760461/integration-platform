package com.incaier.integration.platform.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 医疗设备管理 dto
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MedicalEquipmentDto extends PageDto {

    /**
     * id
     */
    private Integer id;

    /**
     * 设备名称
     */
    @NotBlank(message = "设备名称不可为空")
    private String name;

    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不可为空")
    private String code;

    /**
     * 设备型号
     */
    @NotBlank(message = "设备型号不可为空")
    private String equipmentModel;

    /**
     * 生产厂家
     */
    @NotBlank(message = "生产厂家不可为空")
    private String manufacturer;

    /**
     * 设备图片文件路径
     */
    private String equipmentImage;

    /**
     * 设备类别 id
     */
    @NotNull(message = "设备类别 id 不可为空")
    private Integer equipmentTypeId;

    /**
     * 采购价格
     */
    @NotNull(message = "采购价格不可为空")
    private BigDecimal purchasePrice;

    /**
     * 供应商
     */
    @NotBlank(message = "供应商不可为空")
    private String supplier;

    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不可为空")
    private String contractNumber;

    /**
     * 购置日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "购置日期不可为空")
    private Date purchaseDate;

    /**
     * 使用状态 0 正常使用 1 维修中 2 闲置
     */
    @NotNull(message = "使用状态不可为空")
    private Integer status;

    /**
     * 资产归属部门
     */
    @NotBlank(message = "资产归属部门不可为空")
    private String assetDepartment;

    /**
     * 资产责任人
     */
    private String assetResponsiblePerson;

    /**
     * 删除附件
     */
    private List<Integer> deleteIds;

    /**
     * 附件
     */
    private List<MedicalEquipmentFileDto> annex;

    /**
     * 折旧信息
     */
    private String scrapInfo;
}


