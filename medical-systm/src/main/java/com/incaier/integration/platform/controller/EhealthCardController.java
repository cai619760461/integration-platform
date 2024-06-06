package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.request.EhealthCardDto;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.health.EhealthCardRecordInfoVo;
import com.incaier.integration.platform.response.health.EhealthCardRecordVo;
import com.incaier.integration.platform.response.health.EhealthCardStatisticsVo;
import com.incaier.integration.platform.service.EhealthCardLogService;
import com.incaier.integration.platform.service.EhealthCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 首页数据统计展示
 *
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表控制器
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/eHealthCard")
public class EhealthCardController {

    @Autowired
    private EhealthCardService ehealthCardService;

    @Autowired
    private EhealthCardLogService ehealthCardLogService;

    /**
     * 首页统计
     *
     * @return {@link Result}<{@link String}>
     */
    @GetMapping("/statistics")
    public Result<EhealthCardStatisticsVo> statistics() {
        return Result.success(ehealthCardService.statistics());
    }

    /**
     * 申请记录
     *
     * @param ehealthCardDto request
     * @return {@link Result}<{@link PageInfo}<{@link EhealthCardRecordVo}>>
     */
    @PostMapping("/record")
    public Result<PageInfo<EhealthCardRecordVo>> cardRecord(@Validated @RequestBody EhealthCardDto ehealthCardDto) {
        return Result.success(ehealthCardLogService.getCardRecord(ehealthCardDto));
    }

    /**
     * 更新详情
     *
     * @param id log 流水id
     * @return {@link Result}<{@link Map}<{@link String}, {@link EhealthCardRecordVo}>>
     */
    @GetMapping("/updateInfo")
    public Result<Map<String, EhealthCardRecordInfoVo>> updateInfo(@RequestParam("id") Integer id) {
        return Result.success(ehealthCardLogService.getUpdateInfo(id));
    }

    /**
     * 申领记录数据导出
     *
     * @param ehealthCardDto 电子贺卡dto
     * @param response       响应体
     * @throws IOException IOException
     */
    @PostMapping("/export")
    public void export(@Validated @RequestBody EhealthCardDto ehealthCardDto, HttpServletResponse response) throws IOException {
        ehealthCardLogService.export(ehealthCardDto, response);
    }
}