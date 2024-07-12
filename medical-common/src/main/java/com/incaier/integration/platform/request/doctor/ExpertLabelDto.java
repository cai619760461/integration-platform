package com.incaier.integration.platform.request.doctor;

import com.incaier.integration.platform.request.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 专家标签数据
 *
 * @author weijie.cai
 * @description 专家标签数据
 * @date 2024-06-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExpertLabelDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 5253880804288457412L;

    /**
    * 主键
    */
    private Integer id;

    /**
     * 字典id
     */
    private Integer dictId;

    public ExpertLabelDto(Integer dictId) {
        this.dictId = dictId;
    }


}