package com.incaier.integration.platform.request.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 医生详情请求体
 *
 * @author weijie.cai
 * @description 医生详情请求体
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailDto implements Serializable {

    private static final long serialVersionUID = -3574470065086310829L;

    /**
     * 医生基本信息
     */
    @Valid
    @NotNull(message = "用户基本信息不可为空")
    private DoctorInfoDto doctorInfo;

    /**
     * 医生教育经历
     */
    private DoctorEducationDto doctorEducation;

    /**
     * 医生资格信息
     */
    private DoctorQualificationDto doctorQualification;

    /**
     * 医生执业信息
     */
    private DoctorPracticepointDto doctorPracticepoint;

    /**
     * 医生执业信息
     */
    private List<DoctorPracticepointItemDto> doctorPracticepointItems;
}