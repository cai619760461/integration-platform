package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorQualification;
import com.incaier.integration.platform.request.doctor.DoctorQualificationDto;
import com.incaier.integration.platform.response.doctor.DoctorQualificationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 医生资格信息表 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Mapper
@DS("byIntegrationPlatform")
public interface DoctorQualificationMapper extends BaseMapper<DoctorQualification> {

    /**
     * 取得医生资格
     *
     * @param doctorId 医生id
     * @return {@link DoctorQualificationVo}
     */
    DoctorQualificationVo getDoctorQualificationById(@Param("doctorId") Integer doctorId);

    /**
     * 保存或更新
     *
     * @param doctorQualification 医生资格
     * @return {@link Boolean}
     */
    Boolean saveOrUpdate(DoctorQualificationDto doctorQualification);
}
