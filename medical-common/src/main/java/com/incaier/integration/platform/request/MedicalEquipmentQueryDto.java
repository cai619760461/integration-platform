package com.incaier.integration.platform.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 医疗设备管理 dto
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MedicalEquipmentQueryDto extends PageDto {

    /**
     * 设备名称 模糊匹配
     */
    private String name;

    /**
     * 设备编号
     */
    private String code;
}


