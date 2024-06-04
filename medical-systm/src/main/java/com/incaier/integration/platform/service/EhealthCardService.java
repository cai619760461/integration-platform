package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.EhealthCard;
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
     * @param ehealthCard 请求 body
     * @return {@link String}
     */
    String statistics(EhealthCard ehealthCard);

    /**
     * 获取申请记录
     *
     * @return {@link PageInfo}<{@link String}>
     */
    PageInfo<String> getApplicationRecord();

    /**
     * 获取更新记录
     *
     * @return {@link PageInfo}<{@link String}>
     */
    PageInfo<String> getUpdateRecord();
}