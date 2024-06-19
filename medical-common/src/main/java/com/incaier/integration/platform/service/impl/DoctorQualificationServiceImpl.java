package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.doctor.DoctorQualification;
import com.incaier.integration.platform.mapper.DoctorQualificationMapper;
import com.incaier.integration.platform.service.DoctorQualificationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生资格信息表 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Service
@DS("testMedicalManage")
public class DoctorQualificationServiceImpl extends ServiceImpl<DoctorQualificationMapper, DoctorQualification> implements DoctorQualificationService {

}
