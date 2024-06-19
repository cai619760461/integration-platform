package com.incaier.integration.platform.entity.doctor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 医生评分历史
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
@Data
@TableName("doctor_score_history")
public class DoctorScoreHistory implements Serializable {

    private static final long serialVersionUID = 5653549359493279842L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医生id
     */
    @TableField("doctor_id")
    private Integer doctorId;

    /**
     * 事件类型 0 减分 1 加分
     */
    @TableField("event_type")
    private Integer eventType;

    /**
     * 加减分值
     */
    @TableField("score")
    private Integer score;

    /**
     * 加减分描述
     */
    @TableField("reason")
    private String reason;

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
