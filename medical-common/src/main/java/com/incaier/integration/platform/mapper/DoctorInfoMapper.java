package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorInfo;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.response.doctor.DoctorVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 医师基本信息 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Mapper
@DS("testMedicalManage")
public interface DoctorInfoMapper extends BaseMapper<DoctorInfo> {

    /**
     * 获取医生列表
     *
     * @param dto request
     * @return {@link List}<{@link DoctorInfo}>
     */
    List<DoctorVo> getDoctorList(DoctorQueryDto dto);
}
