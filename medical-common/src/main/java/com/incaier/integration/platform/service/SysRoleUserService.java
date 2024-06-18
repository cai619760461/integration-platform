package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.entity.SysRoleUser;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;

/**
 * sys角色用户服务
 *
 * @author caiweijie
 * @date 2024/06/17
 */
public interface SysRoleUserService extends IService<SysRoleUser> {

    /**
     * 保存角色
     *
     * @param doctorInfoDto 医生信息dto
     * @param newPersonnel  新员工
     */
    void saveRoles(DoctorInfoDto doctorInfoDto, Personnel newPersonnel);
}
