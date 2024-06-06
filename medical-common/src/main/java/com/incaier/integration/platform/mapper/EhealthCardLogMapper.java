package com.incaier.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.EhealthCardLog;
import com.incaier.integration.platform.request.EhealthCardDto;
import com.incaier.integration.platform.response.health.EhealthCardRecordVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 健康卡操作日志
 *
 * @author weijie.cai
 * @description 用户电子健康卡变更日志Mapper
 * @date 2024-06-04
 */
@Mapper
public interface EhealthCardLogMapper extends BaseMapper<EhealthCardLog> {

    /**
     * 获取申领记录
     *
     * @param dto 到
     * @return {@link List}<{@link EhealthCardRecordVo}>
     */
    List<EhealthCardRecordVo> getCardRecord(EhealthCardDto dto);

    /**
     * 获取申领记录总数
     *
     * @param dto 到
     * @return long
     */
    int getCardRecordCount(EhealthCardDto dto);
}