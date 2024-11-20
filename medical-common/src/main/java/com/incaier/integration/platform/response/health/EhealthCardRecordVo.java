package com.incaier.integration.platform.response.health;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.incaier.integration.platform.handler.DataJsonSerializer;
import com.incaier.integration.platform.handler.excel.ExcelConverter;
import com.incaier.integration.platform.handler.SensitiveDataJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户电子健康卡操作记录
 *
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表
 * @date 2024-06-04
 */
@Data
public class EhealthCardRecordVo implements Serializable {

    private static final long serialVersionUID = -5737001248373685369L;

    /**
     * 主键
     */
    @ExcelProperty("序号")
    private Long id;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ExcelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 电子健康卡号
     */
    @ExcelProperty("电子健康卡号")
    private String cardNo;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号", converter = ExcelConverter.IdCardConverter.class)
    @JsonSerialize(using = SensitiveDataJsonSerializer.IdCardSerializer.class)
    private String idNo;

    /**
     * 生日
     */
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 卡主姓名
     */
    @ExcelProperty(value = "姓名", converter = ExcelConverter.NameConverter.class)
    @JsonSerialize(using = SensitiveDataJsonSerializer.NameSerializer.class)
    private String name;

    /**
     * 卡主性别
     */
    @ExcelProperty(value = "性别", converter = ExcelConverter.GenderConverter.class)
    @JsonSerialize(using = DataJsonSerializer.GenderSerializer.class)
    private String sex;

    /**
     * 卡主手机号
     */
    @ExcelProperty("手机号")
    private String phone;

    /**
     * 卡主民族
     */
    @ExcelProperty("民族")
    private String nation;

    /**
     * 户籍所在地
     */
    @ExcelProperty("户籍所在地")
    private String residentAddress;

    /**
     * 申领地
     */
    @ExcelProperty("申领地")
    private String applyAddress;
}