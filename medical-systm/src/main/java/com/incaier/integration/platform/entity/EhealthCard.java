package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户电子健康卡基本信息表
 *
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表
 * @date 2024-06-04
 */
@Data
@TableName(value = "ehealth_card")
public class EhealthCard implements Serializable {

    private static final long serialVersionUID = 4637678975604866600L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 医院/机构id
    */
    private String hospitalId;

    /**
    * 医院/机构名称
    */
    private String hospitalName;

    /**
    * 关联用户user_patien_card表的id
    */
    private String patientCardId;

    /**
    * 卡主名称
    */
    private String name;

    /**
    * 卡主身份证号
    */
    private String idNo;

    /**
    * 卡主性别 1-男 2-女 9-未知
    */
    private String sex;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 卡主民族
    */
    private String nation;

    /**
    * 申领的健康卡号
    */
    private String cardNo;

    /**
    * 户籍所在地
    */
    private String residentAddress;

    /**
    * 申领地址
    */
    private String applyAddress;

    /**
    * create_time
    */
    private LocalDateTime createTime;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 修改时间
    */
    private LocalDateTime updateTime;

    /**
    * 修改人
    */
    private String updateBy;

    /**
    * 注销卡时间
    */
    private LocalDateTime cancelTime;

    /**
    * 注销人
    */
    private String cancelBy;

    /**
    * 卡当前状态
    */
    private String status;
}