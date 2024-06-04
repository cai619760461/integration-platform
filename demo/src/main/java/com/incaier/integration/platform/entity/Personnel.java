package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yan
 * @since 2020-09-16
 */
@Data
@TableName(autoResultMap = true)
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;

    public Personnel() {
    }

    public Personnel(String healthCareProviderId, String domainId) {
        this.healthCareProviderId = healthCareProviderId;
        this.domainId = domainId;
    }

    public Personnel(String healthCareProviderId, String domainId, String name, String passWord, Date createTime, String pushStatus) {
        this.healthCareProviderId = healthCareProviderId;
        this.domainId = domainId;
        this.name = name;
        this.passWord = passWord;
        this.createTime = createTime;
        this.pushStatus = pushStatus;
    }

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
    @TableField(value = "NAME")
    private String name;

    /**
     * 密码
     */
    @TableField(value = "PASS_WORD")
    private String passWord;

    /**
     * 密码
     */
    @TableField(value = "SM4_PASS_WORD")
    private String sm4PassWord;

    /**
     * 身份证号
     */
    @TableField(value = "IDENTITY_NO")
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
    @TableField(value = "PHONE")
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
    @TableField("DEPT_NAME")
    private String deptName;

    /**
     * 工作地址
     */
    @TableField(value = "WORD_ADDRESS")
    private String wordAddress;

    /**
     * 出生日期
     */
    @TableField(value = "DATE_OF_BIRTH")
    private String dateOfBirth;

    /**
     * 出生地
     */
    @TableField("BIRTH_PLACE")
    private String birthPlace;

    /**
     * 学历
     */
    @TableField("EDUCATION")
    private String education;

    /**
     * 政治面貌
     */
    @TableField(value = "POLITICAL_AFFILIATION")
    private String politicalAffiliation;

    /**
     * 人员类别(在编、返聘...)
     */
    @TableField("PERSONNEL_TYPE")
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
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 推送状态，0初始状态，1已推送，2推送失败
     */
    @TableField("PUSH_STATUS")
    private String pushStatus;

    /**
     * 加密方式
     */
    @TableField("encrypt_mode")
    private String encryptMode;
}
