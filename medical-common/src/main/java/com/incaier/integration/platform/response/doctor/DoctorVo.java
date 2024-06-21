package com.incaier.integration.platform.response.doctor;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.incaier.integration.platform.handler.ExcelConverter;
import com.incaier.integration.platform.response.RoleVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 医生信息响应体
 *
 * @author weijie.cai
 * @description 医生信息响应体
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorVo implements Serializable {

    private static final long serialVersionUID = -8071423081588590038L;

    /**
    * 医生id
    */
    @ExcelProperty("医生id")
    private Integer id;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    private String phoneNumber;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 性别 1 男 2 女 9 未知
     */
    @ExcelProperty(value = "性别", converter = ExcelConverter.GenderConverter.class)
    private Integer sex;

    /**
     * 出生日期
     */
    @ExcelProperty(value = "出生日期", converter = ExcelConverter.LocalDateStringConverter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 执业证书编码
     */
    @ExcelProperty("执业证书编码")
    private String code;

    /**
     * 主要执业机构
     */
    @ExcelProperty("主要执业机构")
    private String practiceOrg;

    /**
     * 执业级别 字典id
     */
    @ExcelIgnore
    private Integer practiceLevelId;

    /**
     * 执业级别
     */
    @ExcelProperty("执业级别")
    private String practiceLevel;

    /**
     * 执业类型 字典id
     */
    @ExcelIgnore
    private Integer practiceTypeId;

    /**
     * 执业类型
     */
    @ExcelProperty("执业类型")
    private String practiceType;

    /**
     * 执业范围 字典id
     */
    @ExcelIgnore
    private Integer practiceItemId;

    /**
     * 执业范围
     */
    @ExcelProperty("执业范围")
    private String practiceItem;

    /**
     * 角色
     */
    @ExcelIgnore
    private List<RoleVO> roles;

    /**
     * 工号
     */
    @ExcelProperty("工号")
    private String userName;

    /**
     * 最后一次登录时间
     */
//    @ExcelProperty("最后一次登录时间")
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;
}