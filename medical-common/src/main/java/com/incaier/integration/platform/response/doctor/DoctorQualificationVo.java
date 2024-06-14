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
 * 医生资格信息表Vo
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorQualificationVo implements Serializable {

    private static final long serialVersionUID = -6449790697285439264L;

    /**
     * 主键 id
     */
    private Integer id;

    /**
     * 资格证书编码
     */
    private String code;

    /**
     * 医师类别
     */
    private String type;

    /**
     * 获得资格年度
     */
    private String qualificationYear;

    /**
     * 发证机关
     */
    private String sendDocumentsOrg;

    /**
     * 签发日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date issueDate;
}
