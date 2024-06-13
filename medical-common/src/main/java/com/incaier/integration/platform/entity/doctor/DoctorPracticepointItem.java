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
 * 医生执业项信息 多机构备案
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("doctor_practicepoint_item")
public class DoctorPracticepointItem implements Serializable {

    private static final long serialVersionUID = 4532218081929821887L;

    /**
     * 医生执业信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医生执业Id
     */
    @TableField("practicepoint_id")
    private Integer practicepointId;

    /**
     * 机构名称
     */
    @TableField("name")
    private String name;

    /**
     * 有效开始时间
     */
    @TableField("effective_start_date")
    private Date effectiveStartDate;

    /**
     * 有效结束时间
     */
    @TableField("effective_end_date")
    private Date effectiveEndDate;

    /**
     * 备案机构
     */
    @TableField("remark_org")
    private String remarkOrg;

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
