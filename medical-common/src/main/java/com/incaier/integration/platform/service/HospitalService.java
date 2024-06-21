package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.incaier.integration.platform.entity.Hospital;
import com.incaier.integration.platform.response.HospitalVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 医院信息配置服务层
 * @author weijie.cai
 * @date 2024-06-04
 */
@Service
public interface HospitalService extends IService<Hospital> {

    /**
     * 下拉获取所有医院
     *
     * @return {@link List}<{@link HospitalVo}>
     */
    List<HospitalVo> getHospitalList();
}