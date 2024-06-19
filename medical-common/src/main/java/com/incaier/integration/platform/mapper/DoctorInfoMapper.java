package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorInfo;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.request.doctor.ExpertLabelQueryDto;
import com.incaier.integration.platform.response.doctor.DoctorExpertLabelVo;
import com.incaier.integration.platform.response.doctor.DoctorInfoVo;
import com.incaier.integration.platform.response.doctor.DoctorScoreVo;
import com.incaier.integration.platform.response.doctor.DoctorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 获取医生列表计数
     *
     * @param dto request
     * @return {@link List}<{@link DoctorInfo}>
     */
    Integer getCount(DoctorQueryDto dto);

    /**
     * 获取医生列表
     *
     * @param dto request
     * @return {@link List}<{@link DoctorInfo}>
     */
    List<DoctorVo> getDoctorList(DoctorQueryDto dto);

    /**
     * 保存或更新(工号 user_name 不修改)
     *
     * @param dto request
     * @return {@link Boolean}
     */
    Boolean saveOrUpdate(DoctorInfoDto dto);

    /**
     * 获取专家列表计数
     *
     * @param queryDto 查询dto
     * @return {@link Integer}
     */
    Integer getExpertCount(ExpertLabelQueryDto queryDto);

    /**
     * 获取医生专家列表
     *
     * @param dto request
     * @return {@link List}<{@link DoctorExpertLabelVo}>
     */
    List<DoctorExpertLabelVo> getDoctorExpertList(ExpertLabelQueryDto dto);

    /**
     * 获取医生历史列表计数
     *
     * @param queryDto 查询dto
     * @return {@link Integer}
     */
    Integer getScoreHistoryCount(DoctorQueryDto queryDto);

    /**
     * 获取医生评分表
     *
     * @param dto request
     * @return {@link List}<{@link DoctorScoreVo}>
     */
    List<DoctorScoreVo> getDoctorScoreList(DoctorQueryDto dto);

    /**
     * 通过id获取医生信息
     *
     * @param doctorId 医生id
     * @return {@link DoctorInfoVo}
     */
    DoctorInfoVo getDoctorInfoById(@Param("doctorId") Integer doctorId);
}
