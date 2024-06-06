package com.incaier.integration.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.EhealthCard;
import com.incaier.integration.platform.response.health.EhealthCardStatisticsVo;
import com.incaier.integration.platform.response.statistics.Metrics;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户健康卡信息
 *
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表Mapper
 * @date 2024-06-04
 */
@Mapper
public interface EhealthCardMapper extends BaseMapper<EhealthCard> {

    /**
     * 申领统计
     *
     * @return {@link EhealthCardStatisticsVo}
     */
    EhealthCardStatisticsVo statisticsCount();

    /**
     * 过去月份申领数统计
     *
     * @param monthSize 月份数量
     * @return {@link Map}<{@link String}, {@link Metrics}>
     */
    @MapKey("name")
    Map<String, Metrics> trendsInSize(int monthSize);

    /**
     * 申领年龄分组
     *
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    @MapKey("name")
    Map<String, Metrics> ageGroup();

    /**
     * 申领机构分组Top6
     *
     * @param size 大小
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    List<Map<String, Object>> angeniesTopSix(int size);

    /**
     * 申领地区分组
     *
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    List<Map<String, Object>> regionGroup();

}