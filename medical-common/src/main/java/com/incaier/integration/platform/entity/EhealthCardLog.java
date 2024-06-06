package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description 用户电子健康卡变更日志
 * @author weijie.cai
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "ehealth_card_log")
public class EhealthCardLog implements Serializable {

    private static final long serialVersionUID = 1550562978154731344L;

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 电子健康卡号
    */
    private String cardNo;

    /**
    * 身份证号
    */
    private String idNo;

    /**
    * 卡主姓名
    */
    private String name;

    /**
    * 卡主性别
    */
    private String sex;

    /**
    * 卡主手机号
    */
    private String phone;

    /**
    * 卡主民族
    */
    private String nation;

    /**
    * 户籍所在地
    */
    private String residentAddress;

    /**
    * 申领地
    */
    private String applyAddress;

    /**
    * 创建时间
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
    * 操作类型 字典值 1-注册 2-更新 3-注销
    */
    private String operateType;
}