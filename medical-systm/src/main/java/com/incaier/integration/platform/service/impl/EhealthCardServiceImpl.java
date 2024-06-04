package com.incaier.integration.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.EhealthCard;
import com.incaier.integration.platform.mapper.EhealthCardMapper;
import com.incaier.integration.platform.service.EhealthCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ehealth 服务实现类
 *
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表
 * @date 2024-06-04
 */
@Service
public class EhealthCardServiceImpl extends ServiceImpl<EhealthCardMapper, EhealthCard> implements EhealthCardService {

    @Resource
    private EhealthCardMapper ehealthCardMapper;


    @Override
    public String statistics(EhealthCard ehealthCard) {
        return null;
    }

    @Override
    public PageInfo<String> getApplicationRecord() {
        return null;
    }

    @Override
    public PageInfo<String> getUpdateRecord() {
        return null;
    }
}