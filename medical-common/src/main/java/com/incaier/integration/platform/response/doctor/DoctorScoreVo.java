package com.incaier.integration.platform.response.doctor;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 医生评分概述
 *
 * @author weijie.cai
 * @description 医生评分历史
 * @date 2024-06-12
 */
@Data
public class DoctorScoreVo implements Serializable {

    private static final long serialVersionUID = 6776303287550931769L;

    /**
     * 医生id
     */
    @ExcelProperty("医生id")
    private Integer id;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    private String phoneNumber;

    /**
     * 身份证
     */
    @ExcelProperty("身份证")
    private String identityNo;

    /**
     * 机构id，org-code
     */
    @ExcelProperty("机构code")
    private String orgCode;

    /**
     * 机构名称，org-name
     */
    @ExcelProperty("机构名称")
    private String orgName;

    /**
     * 执业级别
     */
    @ExcelProperty("执业级别")
    private String practiceLevel;

    /**
     * 执业类型
     */
    @ExcelProperty("执业类型")
    private String practiceType;

    /**
     * 当前分数
     */
    @ExcelProperty("当前分数")
    private Integer score;

    /**
     * 修改时间
     */
    @ExcelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastUpdateTime;
}