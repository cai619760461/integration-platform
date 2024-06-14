package com.incaier.integration.platform.response;

import lombok.Data;


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
