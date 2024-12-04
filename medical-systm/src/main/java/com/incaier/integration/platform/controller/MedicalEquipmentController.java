package com.incaier.integration.platform.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.handler.excel.listener.ExcelMedicalEquipmentListener;
import com.incaier.integration.platform.mapper.MedicalEquipmentMapper;
import com.incaier.integration.platform.request.MedicalEquipmentDto;
import com.incaier.integration.platform.request.MedicalEquipmentQueryDto;
import com.incaier.integration.platform.request.excel.ExcelMedicalEquipmentEntity;
import com.incaier.integration.platform.response.MedicalEquipmentDetailVo;
import com.incaier.integration.platform.response.MedicalEquipmentVo;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.service.MedicalEquipmentService;
import com.incaier.integration.platform.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

/**
 * 医疗设备管理
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
@RestController
@RequestMapping("/equipment")
public class MedicalEquipmentController {
    
    @Autowired
    private MedicalEquipmentService medicalequipmentService;

    @Autowired
    private MedicalEquipmentMapper medicalEquipmentMapper;

    /**
     * 列表
     *
     * @param medicalEquipmentDto 医疗设备dto
     * @return {@link Result}<{@link PageInfo}<{@link MedicalEquipmentVo}>>
     */
    @PostMapping(value = "/list")
    public Result<PageInfo<MedicalEquipmentVo>> list(@RequestBody MedicalEquipmentQueryDto medicalEquipmentDto) {
        return Result.success(medicalequipmentService.getList(medicalEquipmentDto));
    }

    /**
     * 根据设备 id 获取设备详情
     *
     * @param id 设备 id
     * @return {@link Result}<{@link MedicalEquipmentDetailVo}>
     */
    @GetMapping(value = "/detail")
    public Result<MedicalEquipmentDetailVo> getDetail(@RequestParam("id") Integer id) {
        return Result.success(medicalequipmentService.getDetail(id));
    }

    /**
     * 新增或编辑
     *
     * @param medicalEquipmentDto 医疗设备dto
     * @return {@link Result}<{@link PageInfo}<{@link MedicalEquipmentVo}>>
     */
    @PostMapping(value = "/saveOrUpdate")
    public Result<Boolean> saveOrUpdateEquipment(@Validated @RequestBody MedicalEquipmentDto medicalEquipmentDto) {
        return Result.success(medicalequipmentService.saveOrUpdateEquipment(medicalEquipmentDto));
    }

    /**
     * 删除设备
     *
     * @param id 身份证件
     * @return {@link Result}<{@link Boolean}>
     */
    @DeleteMapping(value = "/delete")
    public Result<Boolean> delete(@RequestParam("id") Integer id) {
        return Result.success(medicalequipmentService.delete(id));
    }

    /**
     * 导入模板下载
     *
     * @param response response
     */
    @PostMapping("/download/template")
    public void updateDetail(HttpServletResponse response) throws Exception {
        ExcelMedicalEquipmentEntity entity = ExcelMedicalEquipmentEntity.builder()
                .code("S001")
                .name("注射器")
                .equipmentModel("ISO-001-1")
                .manufacturer("西门子")
                .equipmentTypeId(86)
                .purchasePrice(new BigDecimal("12.33"))
                .supplier("无")
                .contractNumber("123152913215")
                .purchaseDate(LocalDate.now())
                .status(0)
                .assetDepartment("无")
                .build();
        ExcelUtil.download("医疗器械导入模板", response, ExcelMedicalEquipmentEntity.class, Collections.singletonList(entity));
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
        EasyExcel.read(file.getInputStream(), ExcelMedicalEquipmentEntity.class, new ExcelMedicalEquipmentListener()).sheet().doRead();
        return Result.success(true);
    }

    /**
     * 医疗设备数据导出
     *
     * @param response  响应体
     * @param queryDto 医疗设备dto
     */
    @PostMapping("/export")
    public void export(@RequestBody MedicalEquipmentQueryDto queryDto, HttpServletResponse response) {
        ExcelUtil.export(queryDto, response,
                d -> medicalEquipmentMapper.getCount(d),
                d -> medicalEquipmentMapper.getList(d),
                MedicalEquipmentVo.class);
    }
}
