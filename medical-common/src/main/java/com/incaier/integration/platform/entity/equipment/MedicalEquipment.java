package com.incaier.integration.platform.entity.equipment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 医疗设备管理
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("medical_equipment")
public class MedicalEquipment implements Serializable {

    private static final long serialVersionUID = 5581549997662415474L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    @TableField("name")
    private String name;

    /**
     * 设备编号
     */
    @TableField("code")
    private String code;

    /**
     * 设备型号
     */
    @TableField("equipment_model")
    private String equipmentModel;

    /**
     * 生产厂家
     */
    @TableField("manufacturer")
    private String manufacturer;

    /**
     * 设备图片文件名
     */
    @TableField("equipment_image")
    private String equipmentImage;

    /**
     * 设备类别
     */
    @TableField("equipment_type")
    private Integer equipmentType;

    /**
     * 设备类别
     */
    @TableField("equipment_type_name")
    private String equipmentTypeName;

    /**
     * 采购价格
     */
    @TableField("purchase_price")
    private BigDecimal purchasePrice;

    /**
     * 供应商
     */
    @TableField("supplier")
    private String supplier;

    /**
     * 合同编号
     */
    @TableField("contract_number")
    private String contractNumber;

    /**
     * 购置日期
     */
    @TableField("purchase_date")
    private Date purchaseDate;

    /**
     * 使用状态 0 正常使用 1 维修中 2 闲置
     */
    @TableField("status")
    private Integer status;

    /**
     * 资产归属部门
     */
    @TableField("asset_department")
    private String assetDepartment;

    /**
     * 资产责任人
     */
    @TableField("asset_responsible_person")
    private String assetResponsiblePerson;

    /**
     * 相关文档文件名
     */
    @TableField("related_document")
    private String relatedDocument;

    /**
     * 折旧信息
     */
    @TableField("scrap_info")
    private String scrapInfo;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    @TableField("is_delete")
    private Integer isDelete;
}
