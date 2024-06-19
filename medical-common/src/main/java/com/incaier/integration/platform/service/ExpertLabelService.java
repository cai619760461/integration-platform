package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.doctor.ExpertLabel;
import com.incaier.integration.platform.request.doctor.ExpertLabelQueryDto;
import com.incaier.integration.platform.response.doctor.DoctorExpertLabelVo;

import java.util.List;

/**
 * <p>
 * 专家标签 服务类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
public interface ExpertLabelService extends IService<ExpertLabel> {

    /**
     * 获取专家列表
     *
     * @param queryDto 查询dto
     * @return {@link PageInfo}<{@link DoctorExpertLabelVo}>
     */
    PageInfo<DoctorExpertLabelVo> getExpertList(ExpertLabelQueryDto queryDto);

    /**
     * 构建专家列表
     *
     * @param dto request
     * @return {@link List}<{@link DoctorExpertLabelVo}>
     */
    List<DoctorExpertLabelVo> buildExpertList(ExpertLabelQueryDto dto);
}
