package com.incaier.integration.platform.request.doctor;

import com.incaier.integration.platform.request.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 专家标签数据
 *
 * @author weijie.cai
 * @description 专家标签数据
 * @date 2024-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExpertLabelQueryDto extends PageDto {

    /**
     * 字典id
     */
    private List<Integer> dictIds;
}