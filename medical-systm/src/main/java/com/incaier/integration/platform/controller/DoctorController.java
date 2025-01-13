package com.incaier.integration.platform.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import com.incaier.integration.platform.handler.excel.listener.ExcelUserListener;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
@Validated
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
        ExcelUtil.download("医生导入模板", response, ExcelDoctorEntity.class, doctorInfoService.getUserTemplate());
    }

    /**
     * 根据模板导入 excel
     *
     * @param file 文件
     * @return {@link Result}<{@link Boolean}>
     * @throws IOException IOException
     */
    @PostMapping("/importExcel")
    @DSTransactional
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

    /**
     * 根据 身份证 和 机构代码 获取医生详情
     *
     * @param orgCode 机构代码
     * @return {@link Result}<{@link PageInfo}<{@link DoctorVo}>>
     */
    @GetMapping("/query")
    public Result<DoctorDetailVo> getDoctorDetail(@NotEmpty(message = "医院代码不可为空") @RequestParam(name = "orgCode") String orgCode,
                                                  @NotEmpty(message = "身份证号不可为空") @Pattern(regexp = "^\\d{17}[\\dXx]$", message = "非18位身份证，格式不正确") @RequestParam(name = "identityNo") String identityNo) {
        return Result.success(doctorInfoService.getDoctorDetailByCode(orgCode, identityNo));
    }
}