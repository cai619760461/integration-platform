package com.incaier.integration.platform.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * base 实体
 *
 * @author caiweijie
 * @date 2024/06/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto implements Serializable {

    private static final long serialVersionUID = -7090498406836718329L;

    /**
     * 医生id
     */
    private Integer doctorId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;
}