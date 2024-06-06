package com.incaier.integration.platform.response.health;

import com.incaier.integration.platform.response.statistics.Metrics;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 电子健康卡数据统计
 *
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表
 * @date 2024-06-04
 */
@Data
public class EhealthCardStatisticsVo implements Serializable {

    private static final long serialVersionUID = 4493980118943224013L;

    /**
     * 总计申领人次
     */
    private Integer total;

    /**
     * 本月申领人次
     */
    private Integer month;

    /**
     * 本日申领人次
     */
    private Integer today;

    /**
     * 申领趋势(年度)
     */
    private List<Metrics> trends;

    /**
     * 申领年龄段统计
     */
    private List<Metrics> ageGroup;

    /**
     * 申领机构排行(TOP6)
     */
    private List<Map<String, Object>> agencyGroup;

    /**
     * 申领地区统计
     */
    private List<Map<String, Object>> regionGroup;
}