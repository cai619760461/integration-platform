package com.incaier.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorQualification;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 医生资格信息表 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Mapper
public interface DoctorQualificationMapper extends BaseMapper<DoctorQualification> {

}
