package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sys用户
 *
 * @author caiweijie
 * @date 2024/06/12
 */
@Data
@TableName(value = "SYS_USER")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -3845253698679841360L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 域ID
     */
    @TableField(value = "DOMAIN_ID")
    private String domainId;

    /**
     * 用户名
     */
    @TableField(value = "USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "PASSWORD")
    private String password;

    /**
     * 职务ID
     */
    @TableField(value = "JOB_CATEGORY_ID")
    private String jobCategoryId;

    /**
     * 职务名称
     */
    @TableField(value = "JOB_CATEGORY_NAME")
    private String jobCategoryName;

    /**
     * 工作地址
     */
    @TableField(value = "WORK_ADDRESS")
    private String workAddress;

    /**
     * 工作联系电话
     */
    @TableField(value = "WORK_PHONE")
    private String workPhone;

    /**
     * 身份证号
     */
    @TableField(value = "IDENTITY_NO")
    private String identityNo;

    /**
     * 姓名
     */
    @TableField(value = "NAME")
    private String name;

    /**
     * 性别代码
     */
    @TableField(value = "SEX_CODE")
    private String sexCode;

    /**
     * 性别
     */
    @TableField(value = "SEX_NAME")
    private String sexName;

    /**
     * 出生日期
     */
    @TableField(value = "DATE_OF_BIRTH")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date  dateOfBirth;

    /**
     * 科室代码
     */
    @TableField(value = "DEPT_ID")
    private String deptId;

    /**
     * 科室名称
     */
    @TableField(value = "DEPT_NAME")
    private String deptName;

    /**
     * 出生地
     */
    @TableField(value = "BIRTH_PLACE")
    private String birthPlace;

    /**
     * 人员类别(在编、返聘...)
     */
    @TableField(value = "PERSONNEL_TYPE")
    private String personnelType;

    /**
     * 邮箱
     */
    @TableField(value = "EMAIL")
    private String email;

    /**
     * 数据权限
     */
    @TableField(value = "DATA_SCOPE")
    private String dataScope;

    /**
     * 最后登陆时间
     */
    @TableField(value = "LAST_LOGIN_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date  lastLoginTime;

    /**
     * 登陆重试次数
     */
    @TableField(value = "LOGIN_COUNT")
    private String loginCount;

    /**
     * 是否锁定：0-未锁定，1-锁定
     */
    @TableField(value = "IS_LOCKED")
    private String isLocked;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date  createTime;
}