package com.incaier.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.Hospital;
import com.incaier.integration.platform.response.HospitalVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 医院信息配置Mapper
 * @author weijie.cai
 * @date 2024-06-04
 */
@Mapper
public interface HospitalMapper extends BaseMapper<Hospital> {

    /**
     * 下拉获取所有机构
     *
     * @return {@link List}<{@link HospitalVo}>
     */
    List<HospitalVo> getAgencies();

}