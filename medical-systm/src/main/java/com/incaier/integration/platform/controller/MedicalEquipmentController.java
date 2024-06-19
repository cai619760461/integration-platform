package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.mapper.MedicalEquipmentMapper;
import com.incaier.integration.platform.request.MedicalEquipmentDto;
import com.incaier.integration.platform.request.MedicalEquipmentQueryDto;
import com.incaier.integration.platform.response.MedicalEquipmentDetailVo;
import com.incaier.integration.platform.response.MedicalEquipmentVo;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.service.MedicalEquipmentService;
import com.incaier.integration.platform.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 医疗设备管理 前端控制器
 * </p>
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

    @PostMapping(value = "/delete/{id}")
    public Result<Object> delete(@PathVariable("id") String id) {
        medicalequipmentService.removeById(id);
        return Result.success("deleted successfully");
    }

    /**
     * 医疗设备数据导出
     *
     * @param response            响应体
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
