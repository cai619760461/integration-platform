package com.incaier.integration.platform.handler.excel.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.entity.equipment.MedicalEquipment;
import com.incaier.integration.platform.handler.excel.valid.ExceptionCustom;
import com.incaier.integration.platform.request.excel.ExcelMedicalEquipmentEntity;
import com.incaier.integration.platform.service.MedicalEquipmentService;
import com.incaier.integration.platform.util.SpringContextUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * excel 机构 监听器
 *
 * @author caiweijie
 * @date 2024/06/17
 */
public class ExcelMedicalEquipmentListener extends ExcelListener<ExcelMedicalEquipmentEntity> {

    private final Logger logger = LoggerFactory.getLogger(ExcelMedicalEquipmentListener.class);

    private final MedicalEquipmentService medicalEquipmentService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public ExcelMedicalEquipmentListener() {
        medicalEquipmentService = SpringContextUtils.getBean(MedicalEquipmentService.class);
    }

    @Override
    public void checkData() {
        List<String> equipmentCodes = this.list.stream().map(ExcelMedicalEquipmentEntity::getCode).collect(Collectors.toList());
        List<MedicalEquipment> orgs = medicalEquipmentService.list(Wrappers.<MedicalEquipment>lambdaQuery().in(MedicalEquipment::getCode, equipmentCodes));
        if (CollectionUtils.isNotEmpty(orgs)) {
            equipmentCodes = orgs.stream().map(MedicalEquipment::getCode).collect(Collectors.toList());
            throw new ExceptionCustom("IMPORT_PARAM_CHECK_FAIL", "机构数据已存在：" + JSON.toJSONString(equipmentCodes));
        }
    }

    /**
     * 加上存储数据库
     */
    @Override
    public void saveData() {
        List<MedicalEquipment> result = this.list.stream().map(equipment -> {
            MedicalEquipment dto = new MedicalEquipment();
            dto.setCreateBy(UserHolder.getUserName());
            dto.setUpdateBy(UserHolder.getUserName());
            BeanUtils.copyProperties(equipment, dto);
            return dto;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(result)) {
            medicalEquipmentService.saveBatch(result);
        }
    }
}