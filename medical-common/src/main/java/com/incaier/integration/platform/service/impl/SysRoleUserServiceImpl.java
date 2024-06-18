package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.entity.SysRoleUser;
import com.incaier.integration.platform.mapper.SysRoleUserMapper;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.service.SysRoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * sys角色用户服务impl
 *
 * @author caiweijie
 * @date 2024/06/17
 */
@Service
@DS("hipPortalCloud")
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

    private final Logger logger = LoggerFactory.getLogger(SysRoleUserServiceImpl.class);

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public void saveRoles(DoctorInfoDto doctorInfoDto, Personnel newPersonnel) {
        logger.info("新建personnel，id：{}", newPersonnel.getPk());
        List<SysRoleUser> roles = doctorInfoDto.getRoles().stream().map(roleVO -> SysRoleUser.builder()
                .roleId(Integer.valueOf(roleVO.getRoleId()))
                .userId(newPersonnel.getPk().intValue()).build()).collect(Collectors.toList());
        sysRoleUserMapper.saveBatch(roles);
    }
}
