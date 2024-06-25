package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.doctor.DoctorEducation;
import com.incaier.integration.platform.mapper.DoctorEducationMapper;
import com.incaier.integration.platform.service.DoctorEducationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生教育经历 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Service
@DS("byIntegrationPlatform")
public class DoctorEducationServiceImpl extends ServiceImpl<DoctorEducationMapper, DoctorEducation> implements DoctorEducationService {

}
