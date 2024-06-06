package com.incaier.integration.platform.response.health;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description 用户电子健康卡变更日志
 * @author weijie.cai
 * @date 2024-06-04
 */
@Data
public class EhealthCardVo implements Serializable {

    private static final long serialVersionUID = -3174513414385338510L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 民族
     */
    private String nation;

    /**
     * 电子健康卡ID
     */
    private String cardNo;

    /**
     * 户籍地
     */
    private String residentAddress;

    /**
     * 申领地
     */
    private String applyAddress;

    /**
     * create_time
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 注销卡时间
     */
    private LocalDateTime cancelTime;

    /**
     * 注销人
     */
    private String cancelBy;

    /**
     * 卡当前状态
     */
    private String status;
}