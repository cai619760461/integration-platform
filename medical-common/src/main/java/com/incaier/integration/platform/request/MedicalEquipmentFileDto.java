package com.incaier.integration.platform.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 医疗设备管理附件 dto
 *
 * @author weijie.cai
 * @description 医疗设备管理
 * @date 2024-06-04
 */
@Data
public class MedicalEquipmentFileDto implements Serializable {

    private static final long serialVersionUID = 7337664233271539357L;

    /**
     * id
     */
    private Integer id;

    /**
     * 设备id
     */
    private Integer equipmentId;

    /**
     * 文件类别 doc,pdf,xls等
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;
}