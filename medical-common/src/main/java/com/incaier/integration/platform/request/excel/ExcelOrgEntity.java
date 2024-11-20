package com.incaier.integration.platform.request.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.incaier.integration.platform.annotation.ExcelValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机构管理
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelOrgEntity {

    /**
     * 机构id
     */
    @ExcelProperty("机构代码")
    @ExcelValid(message = "机构代码必填")
    @ColumnWidth(20)
    private String code;

    /**
     * 机构名称
     */
    @ExcelProperty("机构名称")
    @ExcelValid(message = "机构名称必填")
    @ColumnWidth(20)
    private String name;

    /**
     * 统一信用代码
     */
    @ExcelProperty("统一信用代码")
    @ColumnWidth(20)
    private String unifiedCreditIdentifier;

    /**
     * 机构类型 字典id
     */
    @ExcelProperty("机构类型 字典id")
    @ColumnWidth(20)
    @ExcelValid(message = "机构类型 字典id 必填")
    private Integer typeId;

    /**
     * 机构联系人
     */
    @ExcelProperty("机构联系人")
    @ColumnWidth(20)
    private String contactName;

    /**
     * 机构联系人职位
     */
    @ExcelProperty("机构联系人职位")
    @ColumnWidth(20)
    private String contactTitle;

    /**
     * 机构联系人电话
     */
    @ExcelProperty("机构联系人电话")
    @ColumnWidth(20)
    private String contactPhone;

    /**
     * 区域 字典id
     */
    @ExcelProperty("区域 字典id")
    @ExcelValid(message = "区域 字典id 必填")
    @ColumnWidth(20)
    private Integer districtDictId;

    /**
     * 机构所属县镇
     */
    @ExcelProperty("机构所属县镇")
    @ColumnWidth(20)
    private String county;

    /**
     * 机构地址
     */
    @ExcelProperty("机构地址")
    @ColumnWidth(20)
    private String address;

    /**
     * 是否启用 1启用/0未启用
     */
    @ExcelProperty("是否启用 1启用 0未启用")
    @ExcelValid(message = "是否启用 必填")
    @ColumnWidth(20)
    private Integer enabled;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    @ColumnWidth(20)
    private String remark;
}


