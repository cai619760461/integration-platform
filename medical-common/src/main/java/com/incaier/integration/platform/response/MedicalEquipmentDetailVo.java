package com.incaier.integration.platform.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 医疗设备详情 vo
 *
 * @author weijie.cai
 * @description 医疗设备详情
 * @date 2024-06-04
 */
@Data
public class MedicalEquipmentDetailVo implements Serializable {

    private static final long serialVersionUID = -6018732444111067716L;

    /**
     * id
     */
    private Integer id;

    /**
     * 设备名称
     */
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
     * 设备类别
     */
    private Integer equipmentTypeId;

    /**
     * 设备类别名称
     */
    private String equipmentTypeName;

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
     * 相关文档文件名
     */
    private String relatedDocument;

    /**
     * 折旧信息
     */
    private String scrapInfo;

    /**
     * 附件
     */
    private List<MedicalEquipmentFileVo> annex;
}