package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.EhealthCardLog;
import com.incaier.integration.platform.request.EhealthCardDto;
import com.incaier.integration.platform.response.health.EhealthCardRecordInfoVo;
import com.incaier.integration.platform.response.health.EhealthCardRecordVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 健康卡操作日志服务
 *
 * @author weijie.cai
 * @description 用户电子健康卡变更日志服务层
 * @date 2024-06-04
 */
@Service
public interface EhealthCardLogService extends IService<EhealthCardLog> {

    /**
     * 获取申请记录
     *
     * @param ehealthCardDto request
     * @return {@link PageInfo}<{@link String}>
     */
    PageInfo<EhealthCardRecordVo> getCardRecord(EhealthCardDto ehealthCardDto);

    /**
     * 获取记录信息
     *
     * @param id 身份证件
     * @return {@link Map}<{@link String}, {@link EhealthCardRecordInfoVo}>
     */
    Map<String, EhealthCardRecordInfoVo> getUpdateInfo(Integer id);
}