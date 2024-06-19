package com.incaier.integration.platform.response.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incaier.integration.platform.response.RoleVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 医生基本信息响应体
 *
 * @author weijie.cai
 * @description 医生基本信息响应体
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfoVo implements Serializable {

    private static final long serialVersionUID = 6138178458325904928L;

    /**
     * 医生id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 1 男 2 女 9 未知
     */
    private Integer sex;

    /**
     * 身份证
     */
    private String identityNo;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 民族
     */
    private String ethnicity;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名（工号）
     */
    private String userName;

    /**
     * 角色
     */
    List<RoleVO> roles;

    /**
     * 机构id，org-code
     */
    private String orgCode;

    /**
     * 机构名称，org-code
     */
    private String orgName;

    /**
     * 是否专家 0 不是 1 是
     */
    private Integer isExpert;

    /**
     * 专家标签信息
     */
    private List<LabelVo> expertLabels;
}