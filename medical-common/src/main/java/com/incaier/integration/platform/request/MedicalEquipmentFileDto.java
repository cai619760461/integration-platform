package com.incaier.integration.platform.request;

import com.incaier.integration.platform.response.FileEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 医疗设备管理附件 dto
 *
 * @author weijie.cai
 * @description 医疗设备管理
 * @date 2024-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MedicalEquipmentFileDto extends FileEntity implements Serializable {

    private static final long serialVersionUID = 7337664233271539357L;

    /**
     * id
     */
    private Integer id;

    /**
     * 设备id
     */
    private Integer equipmentId;
}