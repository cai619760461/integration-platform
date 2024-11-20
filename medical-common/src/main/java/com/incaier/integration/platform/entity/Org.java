package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("org")
public class Org implements Serializable {

    private static final long serialVersionUID = 3915658719953126447L;

    /**
    * 机构id
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
    * 机构编码
    */
    private String code;

    /**
    * 机构序号
    */
    private Integer position;

    /**
     * 统一信用代码
     */
    private String unifiedCreditIdentifier;

    /**
     * 机构类型 字典id
     */
    private Integer typeId;

    /**
    * 机构名称
    */
    private String name;

    /**
    * 机构联系人
    */
    private String contactName;

    /**
    * 机构联系人职位
    */
    private String contactTitle;

    /**
    * 机构联系人电话
    */
    private String contactPhone;

    /**
     * 机构所属区域 字典id
     */
    private Integer districtDictId;

    /**
    * 机构所属县镇
    */
    private String county;

    /**
    * 机构地址
    */
    private String address;

    /**
    * 机构图片
    */
    private String picturl;

    /**
    * 机构图片类型
    */
    private String contentType;

    /**
    * 备注
    */
    private String remark;

    /**
    * 是否启用 1启用/0未启用
    */
    private Integer enabled;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    private Integer isDelete;
}