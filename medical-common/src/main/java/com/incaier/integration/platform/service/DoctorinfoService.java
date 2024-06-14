package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.doctor.DoctorInfo;
import com.incaier.integration.platform.request.doctor.DoctorDetailDto;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.response.doctor.DoctorDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorVo;

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
     * @return {@link PageInfo}<{@link DoctorVo}>
     */
    PageInfo<DoctorVo> getDoctorList(DoctorQueryDto queryDto);

    /**
     * 获取医生详细信息
     *
     * @param doctorId doctorId
     * @return {@link DoctorDetailVo}
     */
    DoctorDetailVo getDoctorDetail(Integer doctorId);

    /**
     * 添加医生
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean addDoctor(DoctorDetailDto dto);

    /**
     * 删除医生
     *
     * @param doctorId 医生id
     * @return {@link DoctorDetailVo}
     */
    DoctorDetailVo deleteDoctor(Integer doctorId);
}
