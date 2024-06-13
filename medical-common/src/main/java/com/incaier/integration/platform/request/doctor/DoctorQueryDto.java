package com.incaier.integration.platform.request.doctor;

import com.incaier.integration.platform.request.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机构管理
 *
 * @author caiweijie
 * @date 2024/06/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorQueryDto extends PageDto {

    private Integer id;

    /**
     * 机构 code
     */
    private String orgCode;

    /**
     * 执业级别
     */
    private String practiceLevel;

    /**
     * 执业类型
     */
    private String practiceType;

    /**
     * 执业范围
     */
    private String practiceItem;

    /**
     * 匹配关键字 支持【姓名】模糊搜， 【手机号】精准搜
     */
    private String keyword;
}


