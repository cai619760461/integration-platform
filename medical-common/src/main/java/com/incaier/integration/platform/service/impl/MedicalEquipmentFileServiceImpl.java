package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.equipment.MedicalEquipmentFile;
import com.incaier.integration.platform.mapper.MedicalEquipmentFileMapper;
import com.incaier.integration.platform.service.MedicalEquipmentFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备附件 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
@Service
@DS("byIntegrationPlatform")
public class MedicalEquipmentFileServiceImpl extends ServiceImpl<MedicalEquipmentFileMapper, MedicalEquipmentFile> implements MedicalEquipmentFileService {

}
