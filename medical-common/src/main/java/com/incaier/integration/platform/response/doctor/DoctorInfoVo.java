package com.incaier.integration.platform.response.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 医生信息响应体
 *
 * @author weijie.cai
 * @description 医生信息响应体
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfoVo implements Serializable {

    private static final long serialVersionUID = -8071423081588590038L;

    /**
    * 医生id
    */
    private Integer id;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 0 女 1 男 9 未知
     */
    private Integer sex;

    /**
     * 出生日期
     */
    @JsonFormat( pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    /**
     * 执业证书编码
     */
    private String code;

    /**
     * 主要执业机构
     */
    private String practiceOrg;

    /**
     * 执业级别
     */
    private String practiceLevel;

    /**
     * 执业类型
     */
    private String practiceType;

    /**
     * 执业范围
     */
    private String practiceItem;

    /**
     * 角色编码
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 最后一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;
}