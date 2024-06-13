package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.SysUser;
import com.incaier.integration.platform.mapper.SysUserMapper;
import com.incaier.integration.platform.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * sys用户服务impl
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Service
@DS("hipPortalCloud")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
