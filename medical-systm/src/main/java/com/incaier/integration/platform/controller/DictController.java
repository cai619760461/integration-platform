package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.QueryGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import com.incaier.integration.platform.mapper.SysDictDataMapper;
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

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

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
     * 获取某字典类型下，字典数据（分页）
     *
     * @param sysDictDataDto dto
     * @return {@link Result}<{@link PageInfo}<{@link SysDictTypeVo}>>
     */
    @PostMapping("/dataList")
    public Result<PageInfo<SysDictDataVo>> getDataList(@Validated({QueryGroup.class}) @RequestBody SysDictDataDto sysDictDataDto) {
        return Result.success(sysDictDataService.getDataList(sysDictDataDto));
    }

    /**
     * 获取某字典类型下，字典数据（所有）
     *
     * @param sysDictDataDto dto
     * @return {@link Result}<{@link PageInfo}<{@link SysDictTypeVo}>>
     */
    @PostMapping("/allDataList")
    public Result<List<SysDictDataVo>> getAllDataList(@Validated({QueryGroup.class}) @RequestBody SysDictDataDto sysDictDataDto) {
        return Result.success(sysDictDataMapper.getDataList(sysDictDataDto));
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