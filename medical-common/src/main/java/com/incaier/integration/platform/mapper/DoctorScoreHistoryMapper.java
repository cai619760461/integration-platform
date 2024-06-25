package com.incaier.integration.platform.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorScoreHistory;
import com.incaier.integration.platform.response.doctor.DoctorScoreHistoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 医生评分历史 Mapper 接口
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
@Mapper
@DS("byIntegrationPlatform")
public interface DoctorScoreHistoryMapper extends BaseMapper<DoctorScoreHistory> {

    /**
     * 获取socre医生病史详细信息
     *
     * @param doctorId 医生id
     * @return {@link List}<{@link DoctorScoreHistoryVo}>
     */
    List<DoctorScoreHistoryVo> getDoctorSocreHistoryDetail(@Param("doctorId") Integer doctorId);
}
