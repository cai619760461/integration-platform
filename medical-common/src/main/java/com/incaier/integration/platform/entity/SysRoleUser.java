package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色与用户关联表
 *
 * @author caiweijie
 * @date 2024/06/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "SYS_ROLE_USER")
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = -2217598428737358796L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField(value = "ROLE_ID")
    private Integer roleId;

    /**
     * 用户ID
     */
    @TableField(value = "USER_ID")
    private Integer userId;
}