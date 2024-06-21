package com.incaier.integration.platform.response.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 医生评分历史
 *
 * @author weijie.cai
 * @description 医生评分历史
 * @date 2024-06-12
 */
@Data
public class DoctorScoreHistoryVo implements Serializable {

    private static final long serialVersionUID = 6180749136398007721L;

    /**
     * id
     */
    private Integer id;

    /**
     * 医生id
     */
    private Integer doctorId;

    /**
     * 事件类型 0 减分 1 加分
     */
    private Integer eventType;

    /**
     * 加减分值
     */
    private Integer score;

    /**
     * 加减分描述
     */
    private String reason;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}