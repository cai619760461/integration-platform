package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorEducation;
import com.incaier.integration.platform.request.doctor.DoctorEducationDto;
import com.incaier.integration.platform.response.doctor.DoctorEducationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 医生教育经历 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Mapper
@DS("byIntegrationPlatform")
public interface DoctorEducationMapper extends BaseMapper<DoctorEducation> {

    /**
     * 获取医生教育经历
     *
     * @param doctorId 医生id
     * @return {@link DoctorEducationVo}
     */
    DoctorEducationVo getDoctorEducationById(@Param("doctorId") Integer doctorId);

    /**
     * 保存或更新
     *
     * @param doctorEducation 博士教育
     * @return boolean
     */
    boolean saveOrUpdate(DoctorEducationDto doctorEducation);
}
