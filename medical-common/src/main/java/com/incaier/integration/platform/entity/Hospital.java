package com.incaier.integration.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 医院（机构）
 *
 * @author weijie.cai
 * @description 医院信息配置
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "hospital")
public class Hospital implements Serializable {

    private static final long serialVersionUID = 6261523687147433218L;

    /**
    * id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 医院编码
    */
    private String hospitalId;

    /**
    * 名称
    */
    private String hospitalName;

    /**
    * 医院icon图片
    */
    private String hospitalIconUrl;

    /**
    * 医院服务地址
    */
    private String apiHost;

    /**
    * 医院简介图片
    */
    private String imageUrl;

    /**
    * 医院简介文字
    */
    private String description;

    /**
    * 医院导航图片
    */
    private String navigationImageUrl;

    /**
    * 地址
    */
    private String address;

    /**
    * 联系电话
    */
    private String phone;

    /**
    * 紧急电话
    */
    private String emergencyNumber;

    /**
    * 官网链接
    */
    private String website;

    /**
    * create_by
    */
    private String createBy;

    /**
    * update_by
    */
    private String updateBy;

    /**
    * create_time
    */
    private LocalDateTime createTime;

    /**
    * update_time
    */
    private LocalDateTime updateTime;

    /**
    * 医院性质 字典值
    */
    private String hospitalNature;

    /**
    * 医院等级 字典值
    */
    private String hospitalLevel;

    /**
    * 医院区域id 字典值
    */
    private String areaId;

    /**
    * 状态 字典值 1-是 2-否
    */
    private String status;
}