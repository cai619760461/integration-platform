package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.EhealthCard;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.service.EhealthCardLogService;
import com.incaier.integration.platform.service.EhealthCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weijie.cai
 * @description 用户电子健康卡基本信息表控制器
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/ehealthCard")
public class EhealthCardController {

    @Autowired
    private EhealthCardService ehealthCardService;

    @Autowired
    private EhealthCardLogService ehealthCardLogService;

    /**
     * 首页统计
     */
    @PostMapping("/statistics")
    public Result<String> statistics(@RequestBody EhealthCard ehealthCard) {
        return Result.success(ehealthCardService.statistics(ehealthCard));
    }

    /**
     * 申请记录
     */
    @PostMapping("/application/record")
    public Result<PageInfo<String>> applicationRecord(@RequestBody EhealthCard ehealthCard) {
        return Result.success(ehealthCardService.getApplicationRecord());
    }

    /**
     * 更新记录
     */
    @PostMapping("/update/record")
    public Result<PageInfo<String>> updateRecord(@RequestBody EhealthCard ehealthCard) {
        return Result.success(ehealthCardService.getUpdateRecord());
    }

}