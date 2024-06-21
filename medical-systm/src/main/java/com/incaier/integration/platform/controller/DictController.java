package com.incaier.integration.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.constant.BYConstant;
import com.incaier.integration.platform.entity.SysDictData;
import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.QueryGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import com.incaier.integration.platform.request.SysDictDataDto;
import com.incaier.integration.platform.request.SysDictTypeDto;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.dict.SysDictDataVo;
import com.incaier.integration.platform.response.dict.SysDictTypeVo;
import com.incaier.integration.platform.service.SysDictDataService;
import com.incaier.integration.platform.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典通知
 *
 * @author weijie.cai
 * @description 数据字典
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 获取所有字典类型
     *
     * @param sysDictTypeDto dto
     * @return {@link Result}<{@link PageInfo}<{@link SysDictTypeVo}>>
     */
    @PostMapping("/typeList")
    public Result<PageInfo<SysDictTypeVo>> getTypeList(@RequestBody SysDictTypeDto sysDictTypeDto) {
        return Result.success(sysDictTypeService.getTypeList(sysDictTypeDto));
    }

    /**
     * 获取某字典类型下所有字典数据（分页）
     *
     * @param sysDictDataDto dto
     * @return {@link Result}<{@link PageInfo}<{@link SysDictTypeVo}>>
     */
    @PostMapping("/dataList")
    public Result<PageInfo<SysDictDataVo>> getDataList(@Validated({QueryGroup.class}) @RequestBody SysDictDataDto sysDictDataDto) {
        return Result.success(sysDictDataService.getDataList(sysDictDataDto));
    }

    /**
     * 获取某字典类型下所有有效字典数据（所有）
     *
     * @param dictType dict类型
     * @return {@link Result}<{@link List}<{@link SysDictData}>>
     */
    @GetMapping("/dataList")
    public Result<List<SysDictData>> getAllDataList(@RequestParam("dictType") String dictType) {
        return Result.success(sysDictDataService.list(Wrappers.<SysDictData>lambdaQuery()
                .select(SysDictData::getId, SysDictData::getDictLabel, SysDictData::getDictValue, SysDictData::getDictType, SysDictData::getIsDefault)
                .eq(SysDictData::getDictType, dictType)
                .eq(SysDictData::getStatus, BYConstant.INT_FALSE)
                .orderByAsc(SysDictData::getDictSort)));
    }

    /**
     * 新增或更新字典类型
     *
     * @param sysDictTypeDto dto
     * @return {@link Result}<{@link SysDictTypeVo}>
     */
    @PostMapping("/type/saveOrUpdate")
    public Result<Boolean> typeSaveOrUpdate(@Validated({AddGroup.class, UpdateGroup.class}) @RequestBody SysDictTypeDto sysDictTypeDto) {
        return Result.success(sysDictTypeService.typeSaveOrUpdate(sysDictTypeDto));
    }

    /**
     * 字典类型删除
     *
     * @param id 字典类型主键
     * @return {@link Result}<{@link Boolean}>
     */
    @DeleteMapping("/type/delete")
    public Result<Boolean> typeDelete(@RequestParam Integer id) {
        return Result.success(sysDictTypeService.typeDelete(id));
    }

    /**
     * 新增或更新字典数据
     *
     * @param sysDictDataDto dto
     * @return {@link Result}<{@link SysDictTypeVo}>
     */
    @PostMapping("/data/saveOrUpdate")
    public Result<Boolean> dataAdd(@Validated({AddGroup.class, UpdateGroup.class}) @RequestBody SysDictDataDto sysDictDataDto) {
        return Result.success(sysDictDataService.dataSaveOrUpdate(sysDictDataDto));
    }

    /**
     * 字典数据删除
     *
     * @param id 数据字典主键
     * @return {@link Result}<{@link Boolean}>
     */
    @DeleteMapping("/data/delete")
    public Result<Boolean> dataDelete(@RequestParam Integer id) {
        return Result.success(sysDictDataService.removeById(id));
    }
}