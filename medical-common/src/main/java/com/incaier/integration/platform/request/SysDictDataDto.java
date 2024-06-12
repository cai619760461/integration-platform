package com.incaier.integration.platform.request;

import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.QueryGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典数据 dto
 *
 * @author caiweijie
 * @date 2024/06/04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictDataDto extends PageDto {

    private Integer id;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不可为空", groups = {QueryGroup.class, AddGroup.class, UpdateGroup.class})
    private String dictType;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictLabel;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictValue;

    /**
     * 字典排序
     */
    @NotNull(message = "字典排序不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer dictSort;

    /**
     * 状态（0正常 1停用）
     */
    @NotNull(message = "状态不可为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}


