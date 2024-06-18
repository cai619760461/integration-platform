package com.incaier.integration.platform.request.doctor;

import com.incaier.integration.platform.request.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 医生教育经历请求体
 *
 * @author weijie.cai
 * @description 医生教育经历请求体
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DoctorEducationDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 4946320090915440265L;

    /**
     * 经历 id
     */
    private Integer id;

    /**
     * 医生毕业院校
     */
    private String school;

    /**
     * 医生专业
     */
    private String subject;

    /**
     * 医生学制
     */
    private Integer learnTime;

    /**
     * 医生学历编码
     */
    private String learnLevelCode;

    /**
     * 医生学历名称
     */
    private String learnLevelName;

    /**
     * 医生学位
     */
    private String degree;

    /**
     * 医生毕业时间
     */
    private String graduationTime;

    /**
     * 全日制学历
     */
    private String serviceEducation;

    /**
     * 在职学历
     */
    private String incumbencyDegree;

    /**
     * 学分
     */
    private String credits;

    /**
     * 医生学习培训经历
     */
    private String learnItem;

    /**
     * 医生连续教育
     */
    private String continuousEducational;

    /**
     * 医生工作经历
     */
    private String workItem;

    /**
     * 获奖记录
     */
    private String winningRecord;
}