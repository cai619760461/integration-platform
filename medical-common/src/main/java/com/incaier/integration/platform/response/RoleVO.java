package com.incaier.integration.platform.response;

import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 角色vo
 *
 * @author caiweijie
 * @date 2024/06/14
 */
@Data
public class RoleVO {

    /**
     * 角色id
     */
    @NotNull(message = "角色ID 不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 信息
     */
    private String info;
}
