package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.ExpertLabel;
import com.incaier.integration.platform.request.doctor.ExpertLabelDto;
import com.incaier.integration.platform.response.doctor.LabelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 专家标签 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
@Mapper
@DS("byIntegrationPlatform")
public interface ExpertLabelMapper extends BaseMapper<ExpertLabel> {

    /**
     * 获取医生标签
     *
     * @param doctorId 医生id
     * @return {@link List}<{@link LabelVo}>
     */
    List<LabelVo> getDoctorsLabels(@Param("doctorId") Integer doctorId);

    /**
     * 保存或更新批
     *
     * @param labelDto 标记dto
     * @return {@link Boolean}
     */
    Boolean saveOrUpdate(@Param("label") ExpertLabelDto labelDto);

    /**
     * 获取医生标签字典ID
     *
     * @param doctorId 医生id
     * @return {@link Set}<{@link Integer}>
     */
    Set<Integer> getDoctorLabelDictIds(@Param("doctorId") Integer doctorId);
}
