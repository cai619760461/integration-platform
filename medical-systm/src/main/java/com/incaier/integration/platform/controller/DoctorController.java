package com.incaier.integration.platform.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import com.incaier.integration.platform.handler.excel.ExcelUserListener;
import com.incaier.integration.platform.mapper.DoctorInfoMapper;
import com.incaier.integration.platform.request.doctor.DoctorDetailDto;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.request.excel.ExcelDoctorEntity;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.doctor.DoctorDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorVo;
import com.incaier.integration.platform.service.DoctorInfoService;
import com.incaier.integration.platform.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private DoctorInfoService doctorInfoService;

    @Autowired
    private DoctorInfoMapper doctorInfoMapper;

    /**
     * 获取医生列表
     *
     * @param queryDto request
     * @return {@link Result}<{@link PageInfo}<{@link DoctorVo}>>
     */
    @PostMapping("/list")
    public Result<PageInfo<DoctorVo>> getDoctorList(@RequestBody DoctorQueryDto queryDto) {
        return Result.success(doctorInfoService.getDoctorList(queryDto));
    }

    /**
     * 获取医生详情
     *
     * @param doctorId 医生id
     * @return {@link Result}<{@link PageInfo}<{@link DoctorVo}>>
     */
    @GetMapping("/detail")
    public Result<DoctorDetailVo> getDoctorDetail(@RequestParam(name = "id") Integer doctorId) {
        return Result.success(doctorInfoService.getDoctorDetail(doctorId));
    }

    /**
     * 新增医生
     *
     * @param dto request
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("/add")
    public Result<Boolean> addDoctor(@Validated(AddGroup.class) @RequestBody DoctorDetailDto dto) {
        return Result.success(doctorInfoService.addDoctor(dto));
    }

    /**
     * 更新用户信息
     *
     * @param dto request
     * @return {@link Result}<{@link DoctorDetailVo}>
     */
    @PostMapping("/update")
    public Result<Boolean> updateDetail(@Validated(UpdateGroup.class) @RequestBody DoctorDetailDto dto) {
        return Result.success(doctorInfoService.updateDetail(dto));
    }

    /**
     * 删除 医生
     *
     * @param doctorId 医生id
     * @return {@link Result}<{@link Boolean}>
     */
    @DeleteMapping("/delete")
    public Result<Boolean> deleteDoctor(@RequestParam(name = "id") Integer doctorId) {
        return Result.success(doctorInfoService.deleteDoctor(doctorId));
    }

    /**
     * 导入模板下载
     *
     * @param response response
     */
    @PostMapping("/download/template")
    public void updateDetail(HttpServletResponse response) throws Exception {
        ExcelUtil.download(response, ExcelDoctorEntity.class, doctorInfoService.getUserTemplate());
    }

    /**
     * 根据模板导入 excel
     *
     * @param file 文件
     * @return {@link Result}<{@link Boolean}>
     * @throws IOException IOException
     */
    @PostMapping("/importExcel")
    public Result<Boolean> importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ExcelDoctorEntity.class, new ExcelUserListener()).sheet().doRead();
        return Result.success(true);
    }

    /**
     * 医生数据导出
     *
     * @param response 响应体
     * @param queryDto 查询dto
     */
    @PostMapping("/export")
    public void export(@RequestBody DoctorQueryDto queryDto, HttpServletResponse response) {
        ExcelUtil.export(queryDto, response,
                d -> doctorInfoMapper.getCount(d),
                d -> doctorInfoMapper.getDoctorList(d),
                DoctorVo.class);
    }
}