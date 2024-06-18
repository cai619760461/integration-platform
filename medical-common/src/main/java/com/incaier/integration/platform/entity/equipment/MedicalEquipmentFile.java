package com.incaier.integration.platform.entity.equipment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备附件
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("medical_equipment_file")
public class MedicalEquipmentFile implements Serializable {

    private static final long serialVersionUID = -6884854061519761935L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备id
     */
    @TableField("equipment_id")
    private Integer equipmentId;

    /**
     * 文件类别 doc,pdf,xls等
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    @TableField("is_delete")
    private Integer isDelete;
}
