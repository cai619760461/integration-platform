package com.incaier.integration.platform.response.doctor;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 医生评分历史
 *
 * @author weijie.cai
 * @description 医生评分历史
 * @date 2024-06-12
 */
@Data
public class DoctorScoreHistoryDetailVo implements Serializable {

    private static final long serialVersionUID = -4198827966429397446L;

    /**
     * 医生信息
     */
    private DoctorInfoVo doctorInfo;

    /**
     * 历史记录列表
     */
    private List<DoctorScoreHistoryVo> historyList;
}