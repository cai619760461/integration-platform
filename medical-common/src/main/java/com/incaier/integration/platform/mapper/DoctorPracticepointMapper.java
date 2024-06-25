package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorPracticepoint;
import com.incaier.integration.platform.request.doctor.DoctorPracticepointDto;
import com.incaier.integration.platform.response.doctor.DoctorPracticepointVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 医生执业信息表 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Mapper
@DS("byIntegrationPlatform")
public interface DoctorPracticepointMapper extends BaseMapper<DoctorPracticepoint> {

    /**
     * 获取医生执业信息
     *
     * @param doctorId 医生id
     * @return {@link DoctorPracticepointVo}
     */
    DoctorPracticepointVo getDoctorPracticepointById(@Param("doctorId") Integer doctorId);

    /**
     * 获取医生执业级别、类别
     *
     * @param doctorId 医生id
     * @return {@link DoctorPracticepointVo}
     */
    DoctorPracticepointVo getDoctorPraTypeLevelById(@Param("doctorId") Integer doctorId);

    /**
     * 保存或更新
     *
     * @param doctorPracticepoint 医生执业信息
     * @return {@link Boolean}
     */
    Boolean saveOrUpdate(DoctorPracticepointDto doctorPracticepoint);
}
