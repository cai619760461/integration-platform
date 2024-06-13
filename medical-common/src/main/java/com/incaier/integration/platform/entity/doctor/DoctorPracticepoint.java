package com.incaier.integration.platform.entity.doctor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 医生执业信息表
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("doctor_practicepoint")
public class DoctorPracticepoint implements Serializable {

    private static final long serialVersionUID = -4514078426699610371L;

    /**
     * 医生执业信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医生Id
     */
    @TableField("doctor_id")
    private Integer doctorId;

    /**
     * 执业证书编码
     */
    @TableField("code")
    private String code;

    /**
     * 执业级别
     */
    @TableField("practice_level")
    private String practiceLevel;

    /**
     * 任职资格
     */
    @TableField("practice_title")
    private String practiceTitle;

    /**
     * 发证机关
     */
    @TableField("practice_title_code")
    private String practiceTitleCode;

    /**
     * 执业范围
     */
    @TableField("practice_item")
    private String practiceItem;

    /**
     * 执业类型
     */
    @TableField("practice_type")
    private String practiceType;

    /**
     * 审批机构
     */
    @TableField("check_org")
    private String checkOrg;

    /**
     * 审批日期
     */
    @TableField("check_date")
    private Date checkDate;

    /**
     * 执业地点
     */
    @TableField("practice_address")
    private String practiceAddress;

    /**
     * 主要执业机构
     */
    @TableField("practice_org")
    private String practiceOrg;

    /**
     * 执业机构地址
     */
    @TableField("practice_org_address")
    private String practiceOrgAddress;

    /**
     * 院内科室
     */
    @TableField("practice_dept")
    private String practiceDept;

    /**
     * 执业证书图片1
     */
    @TableField("document1_avatar")
    private String document1Avatar;

    /**
     * 执业证书图片1类型
     */
    @TableField("document1_avatar_type")
    private String document1AvatarType;

    /**
     * 执业证书图片2
     */
    @TableField("document2_avatar")
    private String document2Avatar;

    /**
     * 执业证书图片2类型
     */
    @TableField("document2_avatar_type")
    private String document2AvatarType;

    /**
     * 执业证书图片3
     */
    @TableField("document3_avatar")
    private String document3Avatar;

    /**
     * 执业证书图片3类型
     */
    @TableField("document3_avatar_type")
    private String document3AvatarType;

    /**
     * 是否有效 1有效/0无效
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 职称信息
     */
    @TableField("title_information")
    private String titleInformation;

    /**
     * 是否全科医生
     */
    @TableField("is_general_practitioner")
    private String isGeneralPractitioner;

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
