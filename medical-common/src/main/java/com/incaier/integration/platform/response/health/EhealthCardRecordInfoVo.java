package com.incaier.integration.platform.response.health;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.incaier.integration.platform.handler.DataJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户电子健康卡更新数据详情
 *
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表
 * @date 2024-06-04
 */
@Data
public class EhealthCardRecordInfoVo implements Serializable {

    private static final long serialVersionUID = 2246202781260490828L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 卡主姓名
     */
    private String name;

    /**
     * 卡主性别
     */
    @JsonSerialize(using = DataJsonSerializer.GenderSerializer.class)
    private String sex;

    /**
     * 卡主手机号
     */
    private String phone;

    /**
     * 卡主民族
     */
    private String nation;

    /**
     * 户籍所在地
     */
    private String residentAddress;

    /**
     * 申领地
     */
    private String applyAddress;
}