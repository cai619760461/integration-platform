package com.incaier.integration.platform.request.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.incaier.integration.platform.annotation.ExcelValid;
import com.incaier.integration.platform.handler.excel.ExcelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * excel用户实体
 *
 * @author caiweijie
 * @date 2024/06/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelDoctorEntity {

    /**
     * 域ID
     */
    @ExcelProperty("域ID")
    @ExcelValid(message = "导入参数域ID必填")
    @ColumnWidth(20)
    private String domainId;

    /**
     * 用户名/工号
     */
    @ExcelProperty("用户名（工号）")
    @ExcelValid(message = "导入参数工号必填")
    @ColumnWidth(20)
    private String userName;

    /**
     * 工作联系电话
     */
    @ExcelProperty("联系电话")
    @ColumnWidth(20)
    @ExcelValid(message = "导入参数联系电话必填")
    private String phoneNumber;

    /**
     * 身份证号
     */
    @ExcelProperty("身份证号")
    @ColumnWidth(25)
    @ExcelValid(message = "身份证号必填")
    private String identityNo;

    /**
     * 出生日期 yyyy-mm-dd
     */
    @ExcelProperty(value = "出生日期 yyyy-MM-dd", converter = ExcelConverter.LocalDateStringConverter.class)
    @ColumnWidth(15)
    @ExcelValid(message = "出生日期必填")
    private LocalDate birthday;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @ColumnWidth(15)
    @ExcelValid(message = "导入参数姓名必填")
    private String name;

    /**
     * 性别代码
     */
    @ExcelProperty("性别代码 0 1 9")
    @ColumnWidth(12)
    @ExcelValid(message = "性别代码必填")
    private Integer sex;

    /**
     * 角色 ids
     */
    @ExcelProperty("角色代码,多个\",\"分隔")
    @ColumnWidth(12)
    @ExcelValid(message = "性别代码必填")
    private String roleIds;

    /**
     * 机构 code
     */
    @ExcelProperty("机构 code")
    @ColumnWidth(15)
    @ExcelValid(message = "机构 code 必填")
    private String orgCode;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    @ColumnWidth(15)
    private String email;
}