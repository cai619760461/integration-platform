package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.incaier.integration.platform.entity.EhealthCard;
import com.incaier.integration.platform.response.health.EhealthCardStatisticsVo;
import org.springframework.stereotype.Service;

/**
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表服务层
 * @date 2024-06-04
 */
@Service
public interface EhealthCardService extends IService<EhealthCard> {

    /**
     * 统计
     *
     * @return {@link String}
     */
    EhealthCardStatisticsVo statistics();
}