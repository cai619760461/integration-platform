package com.incaier.integration.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * sys用户
 *
 * @author caiweijie
 * @date 2024/06/12
 */
@Data
public class SysUser {

    private Long id;

    /**
     * 域ID
     */
    private String domainId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 职务ID
     */
    private String jobCategoryId;

    /**
     * 职务名称
     */
    private String jobCategoryName;

    /**
     * 工作地址
     */
    private String workAddress;

    /**
     * 工作联系电话
     */
    private String workPhone;

    /**
     * 身份证号
     */
    private String identityNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别代码
     */
    private String sexCode;

    /**
     * 性别
     */
    private String sexName;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateOfBirth;

    /**
     * 科室代码
     */
    private String deptId;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 出生地
     */
    private String birthPlace;

    /**
     * 人员类别(在编、返聘...)
     */
    private String personnelType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 数据权限
     */
    private String dataScope;

    /**
     * 最后登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 登陆重试次数
     */
    private String loginCount;

    /**
     * 是否锁定：0-未锁定，1-锁定
     */
    private String isLocked;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
