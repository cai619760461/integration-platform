package com.incaier.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.doctor.DoctorPracticepointItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 医生执业项信息 多机构备案 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Mapper
public interface DoctorPracticepointItemMapper extends BaseMapper<DoctorPracticepointItem> {

}
