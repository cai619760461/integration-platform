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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "姓名不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 性别 1 男 2 女 9 未知
     */
    @NotNull(message = "性别不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sex;

    /**
     * 身份证
     */
    @NotEmpty(message = "身份证不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String identityNo;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "出生日期不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private LocalDate birthday;

    /**
     * 民族
     */
    private String ethnicity;

    /**
     * 联系电话
     */
    @NotEmpty(message = "联系电话不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名（工号）
     */
    @NotEmpty(message = "用户名（工号）不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String userName;

    /**
     * 角色
     */
    List<RoleVO> roles;

    /**
     * 机构id，org-code
     */
    @NotNull(message = "机构编码不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String orgCode;

    /**
     * 域id
     */
    private String domainId;
}