package com.incaier.integration.platform.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.doctor.DoctorPracticepointItem;
import com.incaier.integration.platform.mapper.DoctorPracticepointItemMapper;
import com.incaier.integration.platform.service.DoctorpracticepointitemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生执业项信息 多机构备案 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Service
@DS("testMedicalManage")
public class DoctorPracticepointItemServiceImpl extends ServiceImpl<DoctorPracticepointItemMapper, DoctorPracticepointItem> implements DoctorpracticepointitemService {

}
