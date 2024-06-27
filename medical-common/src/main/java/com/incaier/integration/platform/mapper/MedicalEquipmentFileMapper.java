package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.equipment.MedicalEquipmentFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 设备附件 Mapper 接口
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
@Mapper
@DS("byIntegrationPlatform")
public interface MedicalEquipmentFileMapper extends BaseMapper<MedicalEquipmentFile> {

    /**
     * 保存或更新批
     *
     * @param files 文件夹
     */
    void saveOrUpdateBatch(@Param("files") List<MedicalEquipmentFile> files);
}
