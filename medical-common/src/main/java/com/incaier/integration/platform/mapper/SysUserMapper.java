package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys用户映射器
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Mapper
@DS("hipPortalCloud")
public interface SysUserMapper extends BaseMapper<SysUser> {
}