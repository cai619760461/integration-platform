package com.incaier.integration.platform.controller;

import com.incaier.integration.platform.response.HospitalVo;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 通用通知
 *
 * @author weijie.cai
 * @description 通用通知
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 下拉获取所有医院
     */
    @GetMapping("/agencies")
    public Result<List<HospitalVo>> getHospitalList() {
        return Result.success(hospitalService.getHospitalList());
    }
}