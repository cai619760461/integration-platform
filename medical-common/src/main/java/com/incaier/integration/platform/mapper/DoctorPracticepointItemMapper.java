package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorPracticepointItem;
import com.incaier.integration.platform.response.doctor.DoctorPracticepointItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 医生执业项信息 多机构备案 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Mapper
@DS("testMedicalManage")
public interface DoctorPracticepointItemMapper extends BaseMapper<DoctorPracticepointItem> {

    /**
     * 获取多机构备案信息
     *
     * @param practicepointId 医生实习点
     * @return {@link List}<{@link DoctorPracticepointItemVo}>
     */
    List<DoctorPracticepointItemVo> getDoctorPracticepointItems(@Param("practicepointId") Integer practicepointId);

    /**
     * 保存批次
     *
     * @param items 实体数据
     */
    void saveBatch(@Param("items") List<DoctorPracticepointItem> items);
}
