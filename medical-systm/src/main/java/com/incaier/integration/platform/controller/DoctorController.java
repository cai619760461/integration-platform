package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.request.doctor.DoctorDetailDto;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.doctor.DoctorDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorVo;
import com.incaier.integration.platform.service.DoctorinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * 获取医生列表
     *
     * @param queryDto request
     * @return {@link Result}<{@link PageInfo}<{@link DoctorVo}>>
     */
    @PostMapping("/list")
    public Result<PageInfo<DoctorVo>> getDoctorList(@RequestBody DoctorQueryDto queryDto) {
        return Result.success(doctorinfoService.getDoctorList(queryDto));
    }

    /**
     * 获取医生详情
     *
     * @param doctorId 医生id
     * @return {@link Result}<{@link PageInfo}<{@link DoctorVo}>>
     */
    @GetMapping("/detail")
    public Result<DoctorDetailVo> getDoctorDetail(@RequestParam(name = "id") Integer doctorId) {
        return Result.success(doctorinfoService.getDoctorDetail(doctorId));
    }

    /**
     * 新增医生
     *
     * @param dto request
     * @return {@link Result}<{@link DoctorDetailVo}>
     */
    @PostMapping("/add")
    public Result<Boolean> addDoctor(@Validated @RequestBody DoctorDetailDto dto) {
        return Result.success(doctorinfoService.addDoctor(dto));
    }

    /**
     * 编辑
     *
     * @param doctorId 医生id
     * @return {@link Result}<{@link PageInfo}<{@link DoctorVo}>>
     */
    @PostMapping("/edit")
    public Result<DoctorDetailVo> editDetail(@RequestParam(name = "id") Integer doctorId) {
        return Result.success(doctorinfoService.getDoctorDetail(doctorId));
    }

    /**
     * 删除 医生
     *
     * @param doctorId 医生id
     * @return {@link Result}<{@link PageInfo}<{@link DoctorVo}>>
     */
    @DeleteMapping("/delete")
    public Result<DoctorDetailVo> deleteDoctor(@RequestParam(name = "id") Integer doctorId) {
        return Result.success(doctorinfoService.deleteDoctor(doctorId));
    }
}