package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.doctor.DoctorInfo;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.response.doctor.DoctorInfoVo;

/**
 * <p>
 * 医师基本信息 服务类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
public interface DoctorinfoService extends IService<DoctorInfo> {

    /**
     * 获取医生列表
     *
     * @param queryDto dto
     * @return {@link PageInfo}<{@link DoctorInfoVo}>
     */
    PageInfo<DoctorInfoVo> getDoctorList(DoctorQueryDto queryDto);
}
