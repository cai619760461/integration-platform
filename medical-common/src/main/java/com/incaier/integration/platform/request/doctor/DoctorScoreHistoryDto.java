package com.incaier.integration.platform.request.doctor;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 医生评分 dto
 *
 * @author weijie.cai
 * @date 2024/06/19
 */
@Data
public class DoctorScoreHistoryDto implements Serializable {

    private static final long serialVersionUID = 3560400403545222571L;

    /**
     * 医生id
     */
    @NotNull(message = "医生id不可为空")
    private Integer doctorId;

    /**
     * 事件类型 0 减分 1 加分
     */
    @NotNull(message = "事件类型不可为空")
    private Integer eventType;

    /**
     * 加减分值
     */
    @NotNull(message = "分值不可为空")
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
     * 更新人
     */
    private String updateBy;
}
