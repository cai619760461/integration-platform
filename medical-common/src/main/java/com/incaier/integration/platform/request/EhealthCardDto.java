package com.incaier.integration.platform.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 健康卡片 dto
 *
 * @author caiweijie
 * @date 2024/06/04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EhealthCardDto extends PageDto {

    /**
     * 查询类型 1 申领 2 更新 3 注销
     */
    @NotBlank(message = "类型不可为空")
    private String operateType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 机构（医院id）
     */
    private String hosptialId;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 户籍所在地
     */
    private String residentAddress;

    /**
     * 申领地址
     */
    private String applyAddress;

    /**
     * 申领的健康卡号
     */
    private String cardNo;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 起始时间
     */
    private String endTime;

    /**
     * 全量导出
     */
    private Integer isAll;

    /**
     * 上一批导出任务最有一个id
     */
    private Long lastId;
}


