package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 全体人员
 *
 * @author 大超
 * @date 2021/9/7 16:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("personnel")
public class Personnel implements Serializable {

    private static final long serialVersionUID = 7834859451205425050L;

    /**
     * 主键
     */
    @TableId(value = "PK", type = IdType.AUTO)
    private Long pk;

    /**
     * 工号
     */
    @TableField("HEALTH_CARE_PROVIDER_ID")
    private String healthCareProviderId;

    /**
     * 所属机构域ID
     */
    @TableField("DOMAIN_ID")
    private String domainId;

    /**
     * 主索引ID
     */
    @TableField("GLOBAL_ID")
    private String globalId;

    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;

    /**
     * 密码
     */
    @TableField("PASS_WORD")
    private String passWord;

    /**
     * 身份证号
     */
    @TableField("IDENTITY_NO")
    private String identityNo;

    /**
     * 证件类别代码
     */
    @TableField("ID_CATEGORY")
    private String idCategory;

    /**
     * 证件类别代码描述
     */
    @TableField("ID_CATEGORY_DES")
    private String idCategoryDes;

    /**
     * 电话
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 性别代码
     */
    @TableField("GENDER_ID")
    private String genderId;

    /**
     * 性别代码对应的名字
     */
    @TableField("GENDER_DEPICT")
    private String genderDepict;

    /**
     * 职务ID
     */
    @TableField("JOB_CATEGORY_ID")
    private String jobCategoryId;

    /**
     * 职务名称
     */
    @TableField("JOB_CATEGORY_NAME")
    private String jobCategoryName;

    /**
     * 科室编码
     */
    @TableField("DEPT_ID")
    private String deptId;

    /**
     * 科室名称
     */
    @TableField(value = "DEPT_NAME")
    private String deptName;

    /**
     * 工作地址
     */
    @TableField("WORD_ADDRESS")
    private String wordAddress;

    /**
     * 出生日期
     */
    @TableField("DATE_OF_BIRTH")
    private String dateOfBirth;

    /**
     * 出生地
     */
    @TableField(value = "BIRTH_PLACE")
    private String birthPlace;

    /**
     * 学历
     */
    @TableField("EDUCATION")
    private String education;

    /**
     * 政治面貌
     */
    @TableField("POLITICAL_AFFILIATION")
    private String politicalAffiliation;

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
     * 有效期开始
     */
    @TableField("EFFECTIVE_TIME_LOW")
    private String effectiveTimeLow;

    /**
     * 有效期结束
     */
    @TableField("EFFECTIVE_TIME_HIGH")
    private String effectiveTimeHigh;

    /**
     * 申请人ID
     */
    @TableField("AUTHOR_ID")
    private String authorId;

    /**
     * 申请人姓名
     */
    @TableField("AUTHOR_NAME")
    private String authorName;

    /**
     * 联系人姓名
     */
    @TableField("REPRESENTED_PERSONNEL_NAME")
    private String representedPersonnelName;

    /**
     * 联系人科室ID
     */
    @TableField("REPRESENTED_DEPT_ID")
    private String representedDeptId;

    /**
     * 联系人科室名
     */
    @TableField("REPRESENTED_DEPT_NAME")
    private String representedDeptName;

    /**
     * 状态 active
     */
    @TableField("STATUS_CODE")
    private String statusCode;


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
    private LocalDateTime lastLoginTime;
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
    private LocalDateTime createTime;

    /**
     * 推送状态，0初始状态，1已推送，2推送失败
     */
    @TableField("PUSH_STATUS")
    private String pushStatus;


    /**
     * 曾用名 +
     */
    @TableField(value = "OTHER_NAME")
    private String otherName;

    /**
     * ABO血型代码 +
     */
    @TableField(value = "ABO_CODE")
    private String aboCode;

    /**
     * ABO血型 +
     */
    @TableField(value = "ABO_NAME")
    private String aboName;

    /**
     * Rh血型代码 +
     */
    @TableField(value = "RH_CODE")
    private String rhCode;

    /**
     * Rh血型 +
     */
    @TableField(value = "RH_NAME")
    private String rhName;

    /**
     * 国籍代码 +
     */
    @TableField(value = "NATION_ALITY_CODE")
    private String nationAlityCode;

    /**
     * 国籍 +
     */
    @TableField(value = "NATION_ALITY_NAME")
    private String nationAlityName;

    /**
     * 婚姻状况代码 +
     */
    @TableField(value = "MARITAL_CODE")
    private String maritalCode;

    /**
     * 婚姻状况名称 +
     */
    @TableField(value = "MARITAL_NAME")
    private String maritalName;

    /**
     * 民族代码 +
     */
    @TableField(value = "NATION_CODE")
    private String nationCode;

    /**
     * 民族 +
     */
    @TableField(value = "NATION_NAME")
    private String nationName;
    /**
     * 专业技术职务代码 +
     */
    @TableField(value = "PRO_CODE")
    private String proCode;

    /**
     * 专业技术职务名称 +
     */
    @TableField(value = "PRO_NAME")
    private String proName;

    /**
     * 毕业院校名称 +
     */
    @TableField(value = "SCHOOL_NAME")
    private String schoolName;

    /**
     * 政治面貌代码 +
     */
    @TableField(value = "POLITICAL_AFFILIATION_CODE")
    private String politicalAffiliationCode;

    /**
     * 学历代码 +
     */
    @TableField(value = "EDUCATION_CODE")
    private String educationCode;

    /**
     * 人员主键
     */
    private String personKey;

    /**
     * 人员状态 1：在职 0：离职
     */
    private String personStatus;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 人员编码
     */
    private String personCode;

    /**
     * 公司主键
     */
    private String companyKey;

    /**
     * 集团主键
     */
    private String groupKey;

    /**
     * 职务级别主键
     */
    private String jobCategoryLevel;

    /**
     * 岗位主键
     */
    private String stationKey;

    /**
     * 是否启用 1是 0否
     */
    private String useFlag;

    /**
     * 删除标志 1是 0否
     */
    private String deleteFlag;
}
