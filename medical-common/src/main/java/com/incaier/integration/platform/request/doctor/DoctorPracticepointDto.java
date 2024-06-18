package com.incaier.integration.platform.request.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incaier.integration.platform.request.BaseDto;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 医生执业信息 请求体
 * </p>
 *
 * @author weijie.cai  医生执业信息表 请求体
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class DoctorPracticepointDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 5509823700561531640L;

    /**
     * 医生执业信息id
     */
    private Integer id;

    /**
     * 执业证书编码
     */
    private String code;

    /**
     * 执业级别
     */
    private String practiceLevel;

    /**
     * 院内科室
     */
    private String practiceDept;

    /**
     * 执业类型
     */
    private String practiceType;

    /**
     * 执业范围
     */
    private String practiceItem;

    /**
     * 主要执业机构
     */
    private String practiceOrg;

    /**
     * 执业机构地址
     */
    private String practiceOrgAddress;

    /**
     * 是否有效 1有效/0无效
     */
    private Integer enabled;

    /**
     * 职称信息
     */
    private String titleInformation;

    /**
     * 审批机构
     */
    private String checkOrg;

    /**
     * 审批日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkDate;

    /**
     * 执业地点
     */
    private String practiceAddress;

    /**
     * 任职资格
     */
    private String practiceTitle;

    /**
     * 是否全科医生
     */
    private String isGeneralPractitioner;
}
