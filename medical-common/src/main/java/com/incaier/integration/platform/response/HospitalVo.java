package com.incaier.integration.platform.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 医院（机构）响应体
 *
 * @author weijie.cai
 * @description 医院信息配置
 * @date 2024-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalVo implements Serializable {

    private static final long serialVersionUID = -58005591328340307L;

    /**
    * id
    */
    private Long id;

    /**
    * 医院编码
    */
    private String hospitalId;

    /**
    * 名称
    */
    private String hospitalName;
}