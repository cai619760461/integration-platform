package com.incaier.integration.platform.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.EhealthCard;
import com.incaier.integration.platform.mapper.EhealthCardMapper;
import com.incaier.integration.platform.response.health.EhealthCardStatisticsVo;
import com.incaier.integration.platform.response.statistics.Metrics;
import com.incaier.integration.platform.service.EhealthCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

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

    @Autowired
    @Qualifier("asyncTaskExecutor")
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    private final static int MONTH_SIZE = 5;

    private final static List<String> AGE_LIST = Arrays.asList("0-17岁", "18-39岁", "40~59岁", "60岁以上", "未知");

    @Override
    public EhealthCardStatisticsVo statistics() {
        // 总计申领人次，本月申领人次，本日申领人次
        EhealthCardStatisticsVo statisticsVo = ehealthCardMapper.statisticsCount();
        // 申领趋势
        CompletableFuture<Void> trend = CompletableFuture.runAsync(() -> statisticsVo.setTrends(monthPadded(ehealthCardMapper.trendsInSize(MONTH_SIZE), MONTH_SIZE)), asyncTaskExecutor);
        // 申领年龄段统计
        CompletableFuture<Void> ages = CompletableFuture.runAsync(() -> statisticsVo.setAgeGroup(agePadded(ehealthCardMapper.ageGroup())), asyncTaskExecutor);
        // 申领机构排行(TOP6)
        CompletableFuture<Void> agencies = CompletableFuture.runAsync(() -> statisticsVo.setAgencyGroup(ehealthCardMapper.angeniesTopSix(6)), asyncTaskExecutor);
        // 申领地区统计
        CompletableFuture<Void> regions = CompletableFuture.runAsync(() -> statisticsVo.setRegionGroup(ehealthCardMapper.regionGroup()), asyncTaskExecutor);
        CompletableFuture.allOf(trend, ages, agencies, regions).join();
        return statisticsVo;
    }

    /**
     * 补齐月份数量
     *
     * @param monthTrend 月份趋势
     * @param size       大小
     * @return {@link List}<{@link Metrics}>
     */
    private List<Metrics> monthPadded(Map<String, Metrics> monthTrend, int size) {
        List<Metrics> lastSizeMonths = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i <= size; i++) {
            String tempMonth = sdf.format(cal.getTime());
            Metrics monthMetrics = new Metrics();
            monthMetrics.setName(tempMonth);
            monthMetrics.setCount(CollectionUtil.isNotEmpty(monthTrend) && monthTrend.containsKey(tempMonth) ? monthTrend.get(tempMonth).getCount() : 0);
            lastSizeMonths.add(monthMetrics);
            cal.add(Calendar.MONTH, -1);
        }
        Collections.reverse(lastSizeMonths);
        return lastSizeMonths;
    }

    /**
     * 补齐年龄段数量
     *
     * @param ageGroup 年龄组
     * @return {@link List}<{@link Metrics}>
     */
    private List<Metrics> agePadded(Map<String, Metrics> ageGroup) {
        List<Metrics> result = new ArrayList<>();
        for (String age : AGE_LIST) {
            Metrics ageMetrics = new Metrics();
            ageMetrics.setName(age);
            ageMetrics.setCount(CollectionUtil.isNotEmpty(ageGroup) && ageGroup.containsKey(age) ? ageGroup.get(age).getCount() : 0);
            result.add(ageMetrics);
        }
        return result;
    }
}