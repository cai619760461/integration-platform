package com.incaier.integration.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.Hospital;
import com.incaier.integration.platform.mapper.HospitalMapper;
import com.incaier.integration.platform.response.HospitalVo;
import com.incaier.integration.platform.service.HospitalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 健康卡操作日志实现类
 *
 * @author weijie.cai
 * @description 用户电子健康卡变更日志
 * @date 2024-06-04
 */
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {

	@Resource
	private HospitalMapper hospitalMapper;


	@Override
	public List<HospitalVo> getAgencies() {
		return hospitalMapper.getAgencies();
	}
}