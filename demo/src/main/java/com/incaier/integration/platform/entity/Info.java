package com.incaier.integration.platform.entity;

import com.incaier.integration.platform.annotation.EncryptField;
import lombok.Data;

/**
 * 信息
 *
 * @author caiweijie
 * @date 2024/05/24
 */
@Data
public class Info {

    @EncryptField
    private String name;

    private String sex;

}
