package com.incaier.integration.platform.entity.doctor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 医生教育经历
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("doctor_education")
public class DoctorEducation implements Serializable {

    private static final long serialVersionUID = 2545302623803689274L;

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
     * 医生毕业院校
     */
    @TableField("school")
    private String school;

    /**
     * 医生专业
     */
    @TableField("subject")
    private String subject;

    /**
     * 医生学制
     */
    @TableField("learn_time")
    private Integer learnTime;

    /**
     * 医生学历编码
     */
    @TableField("learn_level_code")
    private String learnLevelCode;

    /**
     * 医生学历名称
     */
    @TableField("learn_level_name")
    private String learnLevelName;

    /**
     * 医生学位
     */
    @TableField("degree")
    private String degree;

    /**
     * 医生毕业时间
     */
    @TableField("graduation_time")
    private String graduationTime;

    /**
     * 医生学习培训经历
     */
    @TableField("learn_item")
    private String learnItem;

    /**
     * 医生工作经历
     */
    @TableField("work_item")
    private String workItem;

    /**
     * 全日制学历
     */
    @TableField("service_education")
    private String serviceEducation;

    /**
     * 在职学历
     */
    @TableField("incumbency_degree")
    private String incumbencyDegree;

    /**
     * 学分
     */
    @TableField("credits")
    private String credits;

    /**
     * 医生连续教育
     */
    @TableField("continuous_educational")
    private String continuousEducational;

    /**
     * 获奖记录
     */
    @TableField("winning_record")
    private String winningRecord;

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
