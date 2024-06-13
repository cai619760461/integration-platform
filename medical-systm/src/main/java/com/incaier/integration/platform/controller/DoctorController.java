package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.response.HospitalVo;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.doctor.DoctorInfoVo;
import com.incaier.integration.platform.service.DoctorinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 医师维护
 *
 * @author weijie.cai
 * @description 医师维护
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorinfoService doctorinfoService;

    /**
     * 下拉获取所有机构
     *
     * @param queryDto request
     * @return {@link Result}<{@link PageInfo}<{@link DoctorInfoVo}>>
     */
    @PostMapping("/list")
    public Result<PageInfo<DoctorInfoVo>> getDoctorList(@RequestBody DoctorQueryDto queryDto) {
        return Result.success(doctorinfoService.getDoctorList(queryDto));
    }
}