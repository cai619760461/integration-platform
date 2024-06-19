package com.incaier.integration.platform.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.doctor.DoctorPracticepoint;
import com.incaier.integration.platform.mapper.DoctorPracticepointMapper;
import com.incaier.integration.platform.service.DoctorPracticepointService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生执业信息表 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Service
@DS("testMedicalManage")
public class DoctorPracticepointServiceImpl extends ServiceImpl<DoctorPracticepointMapper, DoctorPracticepoint> implements DoctorPracticepointService {

}
