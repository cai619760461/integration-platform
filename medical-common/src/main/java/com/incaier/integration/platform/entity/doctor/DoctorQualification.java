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
 * 医生资格信息表
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("doctor_qualification")
public class DoctorQualification implements Serializable {

    private static final long serialVersionUID = 1919262093145745191L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医生Id
     */
    @TableField("doctor_id")
    private Integer doctorId;

    /**
     * 资格证书编码
     */
    @TableField("code")
    private String code;

    /**
     * 医师类别
     */
    @TableField("type")
    private String type;

    /**
     * 获得资格年度
     */
    @TableField("qualification_year")
    private String qualificationYear;

    /**
     * 发证机关
     */
    @TableField("send_documents_org")
    private String sendDocumentsOrg;

    /**
     * 证书图片1
     */
    @TableField("document1_avatar")
    private String document1Avatar;

    /**
     * 证书图片1类型
     */
    @TableField("document1_avatar_type")
    private String document1AvatarType;

    /**
     * 证书图片2
     */
    @TableField("document2_avatar")
    private String document2Avatar;

    /**
     * 证书图片2类型
     */
    @TableField("document2_avatar_type")
    private String document2AvatarType;

    /**
     * 证书图片3
     */
    @TableField("document3_avatar")
    private String document3Avatar;

    /**
     * 证书图片3类型
     */
    @TableField("document3_avatar_type")
    private String document3AvatarType;

    /**
     * 签发日期
     */
    @TableField("issue_date")
    private Date issueDate;

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
