package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.equipment.MedicalEquipment;
import com.incaier.integration.platform.request.MedicalEquipmentDto;
import com.incaier.integration.platform.request.MedicalEquipmentQueryDto;
import com.incaier.integration.platform.response.MedicalEquipmentVo;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.service.MedicalequipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class MedicalequipmentController {
    
    @Autowired
    private MedicalequipmentService medicalequipmentService;

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
     * 新增或编辑
     *
     * @param medicalEquipmentDto 医疗设备dto
     * @return {@link Result}<{@link PageInfo}<{@link MedicalEquipmentVo}>>
     */
    @PostMapping(value = "/saveOrUpdate")
    public Result<Boolean> saveOrUpdateEquipment(@Validated @RequestBody MedicalEquipmentDto medicalEquipmentDto) {
        return Result.success(medicalequipmentService.saveOrUpdateEquipment(medicalEquipmentDto));
    }



    @GetMapping(value = "/{id}")
    public Result<MedicalEquipment> getById(@PathVariable("id") String id) {
        return Result.success(medicalequipmentService.getById(id));
    }

    @PostMapping(value = "/create")
    public Result<Object> create(@RequestBody MedicalEquipment params) {
        medicalequipmentService.save(params);
        return Result.success("created successfully");
    }

    @PostMapping(value = "/delete/{id}")
    public Result<Object> delete(@PathVariable("id") String id) {
        medicalequipmentService.removeById(id);
        return Result.success("deleted successfully");
    }

    @PostMapping(value = "/update")
    public Result<Object> update(@RequestBody MedicalEquipment params) {
        medicalequipmentService.updateById(params);
        return Result.success("updated successfully");
    }
}
