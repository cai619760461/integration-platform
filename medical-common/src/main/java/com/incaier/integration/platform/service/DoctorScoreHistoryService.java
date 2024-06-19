package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.doctor.DoctorScoreHistory;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.request.doctor.DoctorScoreHistoryDto;
import com.incaier.integration.platform.response.doctor.DoctorScoreHistoryDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorScoreVo;

import java.util.List;

/**
 * <p>
 * 医生评分历史 服务类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
public interface DoctorScoreHistoryService extends IService<DoctorScoreHistory> {

    /**
     * 获取医生评分表
     *
     * @param queryDto 查询dto
     * @return {@link PageInfo}<{@link DoctorScoreVo}>
     */
    PageInfo<DoctorScoreVo> getDoctorScoreList(DoctorQueryDto queryDto);

    /**
     * 构建医生评分表
     *
     * @param dto 到
     * @return {@link List}<{@link DoctorScoreVo}>
     */
    List<DoctorScoreVo> buildDoctorScoreList(DoctorQueryDto dto);

    /**
     * 获取分数历史记录详细信息
     *
     * @param doctorId 医生id
     * @return {@link DoctorScoreHistoryDetailVo}
     */
    DoctorScoreHistoryDetailVo getScoreHistoryDetail(Integer doctorId);

    /**
     * 打分
     *
     * @param dto 到
     * @return {@link Boolean}
     */
    Boolean grade(DoctorScoreHistoryDto dto);
}
