package com.incaier.integration.platform.response.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 医生详情响应体
 *
 * @author weijie.cai
 * @description 医生详情响应体
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailVo implements Serializable {

    private static final long serialVersionUID = -3614535196946671144L;

    /**
     * 医生基本信息
     */
    private DoctorInfoVo doctorInfo;

    /**
     * 医生教育经历
     */
    private DoctorEducationVo doctorEducation;

    /**
     * 医生资格信息
     */
    private DoctorQualificationVo doctorQualification;

    /**
     * 医生执业信息
     */
    private DoctorPracticepointVo doctorPracticepoint;

    /**
     * 多机构备案
     */
    private List<DoctorPracticepointItemVo> doctorPracticepointItems;
}