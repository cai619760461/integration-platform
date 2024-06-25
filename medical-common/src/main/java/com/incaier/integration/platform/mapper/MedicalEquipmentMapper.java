package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.equipment.MedicalEquipment;
import com.incaier.integration.platform.request.MedicalEquipmentQueryDto;
import com.incaier.integration.platform.response.MedicalEquipmentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 医疗设备管理 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
@Mapper
@DS("byIntegrationPlatform")
public interface MedicalEquipmentMapper extends BaseMapper<MedicalEquipment> {

    /**
     * 获取列表计数
     *
     * @param dto reuqest
     * @return {@link List}<{@link MedicalEquipmentVo}>
     */
    Integer getCount(MedicalEquipmentQueryDto dto);

    /**
     * 获取列表
     *
     * @param dto reuqest
     * @return {@link List}<{@link MedicalEquipmentVo}>
     */
    List<MedicalEquipmentVo> getList(MedicalEquipmentQueryDto dto);

    /**
     * 保存或更新
     *
     * @param medicalEquipment 医疗设备
     * @return {@link Boolean}
     */
    Boolean saveOrUpdate(MedicalEquipment medicalEquipment);
}
