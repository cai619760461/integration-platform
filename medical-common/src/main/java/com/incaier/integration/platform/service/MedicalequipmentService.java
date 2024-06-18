package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.equipment.MedicalEquipment;
import com.incaier.integration.platform.request.MedicalEquipmentDto;
import com.incaier.integration.platform.request.MedicalEquipmentQueryDto;
import com.incaier.integration.platform.response.MedicalEquipmentVo;

/**
 * <p>
 * 医疗设备管理 服务类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
public interface MedicalequipmentService extends IService<MedicalEquipment> {

    /**
     * 获取列表
     *
     * @param medicalEquipmentDto 医疗设备dto
     * @return {@link PageInfo}<{@link MedicalEquipmentVo}>
     */
    PageInfo<MedicalEquipmentVo> getList(MedicalEquipmentQueryDto medicalEquipmentDto);

    /**
     * 保存或更新设备
     *
     * @param medicalEquipmentDto 医疗设备dto
     * @return {@link Boolean}
     */
    Boolean saveOrUpdateEquipment(MedicalEquipmentDto medicalEquipmentDto);
}
