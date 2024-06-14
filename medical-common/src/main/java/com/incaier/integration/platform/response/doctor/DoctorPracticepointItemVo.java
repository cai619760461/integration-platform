package com.incaier.integration.platform.response.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 医生执业项信息 多机构备案 Vo
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorPracticepointItemVo implements Serializable {

    private static final long serialVersionUID = -9172594428628869856L;

    /**
     * 医生执业信息id
     */
    private Integer id;

    /**
     * 医生执业Id
     */
    private Integer practicepointId;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 有效开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveStartDate;

    /**
     * 有效结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveEndDate;

    /**
     * 备案机构
     */
    private String remarkOrg;
}
