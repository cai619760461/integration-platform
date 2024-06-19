package com.incaier.integration.platform.response.doctor;

import com.alibaba.excel.annotation.ExcelProperty;
import com.incaier.integration.platform.handler.ExcelConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 医生专家标签数据
 *
 * @author weijie.cai
 * @description 专家标签数据
 * @date 2024-06-12
 */
@Data
public class DoctorExpertLabelVo implements Serializable {

    private static final long serialVersionUID = -4006194141517323343L;

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
     * 专家标签信息
     */
    @ExcelProperty(value = "专家标签信息", converter = ExcelConverter.LabelConverter.class)
    private List<LabelVo> expertLabels;
}