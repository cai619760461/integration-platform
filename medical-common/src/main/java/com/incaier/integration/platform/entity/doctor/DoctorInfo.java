package com.incaier.integration.platform.entity.doctor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 医师基本信息
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("doctor_info")
public class DoctorInfo implements Serializable {

    private static final long serialVersionUID = 2936158307644103037L;

    /**
     * 医生id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别 0 女 1 男 9 未知
     */
    @TableField("sex")
    private Byte sex;

    /**
     * 身份证
     */
    @TableField("identity_no")
    private String identityNo;

    /**
     * 民族
     */
    @TableField("ethnicity")
    private String ethnicity;

    /**
     * 联系电话
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户名（工号）
     */
    @TableField("user_name")
    private String userName;

    /**
     * 角色编码
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 机构id，org-code
     */
    @TableField("district_code")
    private String districtCode;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    @TableField("is_delete")
    private Integer isDelete;
}
