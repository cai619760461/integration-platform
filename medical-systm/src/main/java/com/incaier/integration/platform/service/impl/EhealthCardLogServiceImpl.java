package com.incaier.integration.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.EhealthCardLog;
import com.incaier.integration.platform.mapper.EhealthCardLogMapper;
import com.incaier.integration.platform.service.EhealthCardLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description 用户电子健康卡变更日志
 * @author weijie.cai
 * @date 2024-06-04
 */
@Service
public class EhealthCardLogServiceImpl extends ServiceImpl<EhealthCardLogMapper, EhealthCardLog> implements EhealthCardLogService {

	@Resource
	private EhealthCardLogMapper ehealthCardLogMapper;

}