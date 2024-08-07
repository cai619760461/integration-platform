package com.incaier.integration.platform.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NotNull(message = "设备名称不可为空")
    private String name;

    /**
     * 设备编号
     */
    private String code;

    /**
     * 设备型号
     */
    private String equipmentModel;

    /**
     * 生产厂家
     */
    private String manufacturer;

    /**
     * 设备图片文件路径
     */
    private String equipmentImage;

    /**
     * 设备类别 id
     */
    private Integer equipmentTypeId;

    /**
     * 采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 购置日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    /**
     * 使用状态 0 正常使用 1 维修中 2 闲置
     */
    private Integer status;

    /**
     * 资产归属部门
     */
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


