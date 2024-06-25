package com.incaier.integration.platform.request;

import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 机构管理
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrgDto extends PageDto {

    private Integer id;

    /**
     * 机构id
     */
    @NotNull(message = "机构id不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String code;

    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 统一信用代码
     */
    private String unifiedCreditIdentifier;

    /**
     * 机构类型 字典id
     */
    private Integer typeId;

    /**
     * 机构联系人
     */
    private String contactName;

    /**
     * 机构联系人职位
     */
    private String contactTitle;

    /**
     * 机构联系人电话
     */
    private String contactPhone;

    /**
     * 区域 字典id
     */
    private Integer districtDictId;

    /**
     * 机构所属县镇
     */
    private String county;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 是否启用 1启用/0未启用
     */
    @NotNull(message = "是否启用不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer enabled;

    /**
     * 备注
     */
    private String remark;

    /**
     * 机构名称 搜索关键词
     */
    private String keyword;

}


