package com.incaier.integration.platform.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.doctor.DoctorInfo;
import com.incaier.integration.platform.mapper.DoctorInfoMapper;
import com.incaier.integration.platform.service.DoctorinfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医师基本信息 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Service
@DS("testMedicalManage")
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorinfoService {

}
