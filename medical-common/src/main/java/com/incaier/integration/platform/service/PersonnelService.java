package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;

/**
 * 人员(Personnel)表服务接口
 *
 * @author weijie.cai
 * @since 2023-04-26 12:02:22
 */
public interface PersonnelService extends IService<Personnel> {

    /**
     * 拯救人员
     *
     * @param doctorInfoDto 医生信息dto
     * @return {@link Personnel}
     */
    Personnel savePersonnel(DoctorInfoDto doctorInfoDto);
}