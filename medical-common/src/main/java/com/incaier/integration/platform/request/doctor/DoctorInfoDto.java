package com.incaier.integration.platform.request.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import com.incaier.integration.platform.request.BaseDto;
import com.incaier.integration.platform.response.RoleVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 医生基本信息请求体
 *
 * @author weijie.cai
 * @description 医生基本信息请求体
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DoctorInfoDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 5641037121217443815L;

    /**
     * 医生id
     */
    @NotNull(message = "医生id不可为空", groups = {UpdateGroup.class})
    private Integer id;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 性别 1 男 2 女 9 未知
     */
    @NotNull(message = "性别不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sex;

    /**
     * 职务 字典id
     */
    private Integer positionId;

    /**
     * 身份证
     */
    @NotBlank(message = "身份证不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String identityNo;

    /**
     * 更新身份证
     */
    private String updateIdentityNo;

    /**
     * 证件照地址
     */
    private String identificationPhoto;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "出生日期不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private LocalDate birthday;

    /**
     * 民族
     */
    @NotBlank(message = "民族不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String ethnicity;

    /**
     * 联系电话
     */
//    @NotBlank(message = "联系电话不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名（工号）
     */
    @NotBlank(message = "用户名（工号）不可为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "用户名（工号）不合法", groups = {AddGroup.class, UpdateGroup.class})
    private String userName;

    /**
     * 角色
     */
    @NotNull(message = "用户角色不可为空", groups = {AddGroup.class, UpdateGroup.class})
    @Valid
    private List<@NotNull(message = "用户角色项不可为空", groups = {AddGroup.class, UpdateGroup.class}) RoleVO> roles;

    /**
     * 机构id，org-code
     */
    @NotBlank(message = "机构编码不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String orgCode;

    /**
     * 是否专家
     */
    @NotNull(message = "是否专家不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer isExpert;

    /**
     * 专家标签信息
     */
    private List<Integer> expertLabels;

    /**
     * 域id
     */
    private String domainId;
}