package com.incaier.integration.platform.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.incaier.integration.platform.entity.Personnel;

/**
 * 人员(Personnel)表服务接口
 *
 * @author weijie.cai
 * @since 2023-04-26 12:02:22
 */
@DS("hipmaster")
public interface PersonnelService extends IService<Personnel> {

}