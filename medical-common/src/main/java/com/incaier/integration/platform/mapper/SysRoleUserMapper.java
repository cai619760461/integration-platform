package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys角色用户角色映射器
 *
 * @author caiweijie
 * @date 2024/06/14
 */
@Mapper
@DS("hipPortalCloud")
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    /**
     * 批量保存
     *
     * @param roles 角色
     */
    void saveBatch(@Param("roles") List<SysRoleUser> roles);
}