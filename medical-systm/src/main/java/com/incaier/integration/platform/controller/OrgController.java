package com.incaier.integration.platform.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.constant.BYConstant;
import com.incaier.integration.platform.entity.Org;
import com.incaier.integration.platform.entity.valid.AddGroup;
import com.incaier.integration.platform.entity.valid.UpdateGroup;
import com.incaier.integration.platform.handler.excel.listener.ExcelOrgListener;
import com.incaier.integration.platform.mapper.OrgMapper;
import com.incaier.integration.platform.request.OrgDto;
import com.incaier.integration.platform.request.excel.ExcelOrgEntity;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.org.OrgVo;
import com.incaier.integration.platform.service.OrgService;
import com.incaier.integration.platform.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 机构管理
 *
 * @author weijie.cai
 * @description 机构管理
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @Autowired
    private OrgMapper orgMapper;

    /**
     * 获取所有机构
     *
     * @param orgDto org dto
     * @return {@link Result}<{@link PageInfo}<{@link OrgVo}>>
     */
    @PostMapping("/list")
    public Result<PageInfo<OrgVo>> orgList(@RequestBody OrgDto orgDto) {
        return Result.success(orgService.getOrgList(orgDto));
    }

    /**
     * 新增或更新机构
     *
     * @param orgDto org dto
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("/saveOrUpdate")
    public Result<Boolean> saveOrUpdateOrg(@Validated({AddGroup.class, UpdateGroup.class}) @RequestBody OrgDto orgDto) {
        return Result.success(orgService.saveOrUpdateOrg(orgDto));
    }

    /**
     * 机构删除
     *
     * @param id 数据字典主键
     * @return {@link Result}<{@link Boolean}>
     */
    @DeleteMapping("/delete")
    public Result<Boolean> dataDelete(@RequestParam Integer id) {
        return Result.success(orgService.update(Wrappers.<Org>lambdaUpdate()
                .eq(Org::getId, id)
                .set(Org::getIsDelete, BYConstant.INT_TRUE)));
    }

    /**
     * 导入模板下载
     *
     * @param response response
     */
    @PostMapping("/download/template")
    public void updateDetail(HttpServletResponse response) throws Exception {
        ExcelOrgEntity entity = ExcelOrgEntity.builder()
                .code("医院代码 by_hosptial")
                .name("医院名称")
                .typeId(16)
                .districtDictId(6)
                .enabled(1)
                .build();
        ExcelUtil.download("机构导入模板", response, ExcelOrgEntity.class, Collections.singletonList(entity));
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
        EasyExcel.read(file.getInputStream(), ExcelOrgEntity.class, new ExcelOrgListener()).sheet().doRead();
        return Result.success(true);
    }

    /**
     * 机构数据导出
     *
     * @param response 响应体
     * @param orgDto   org dto
     */
    @PostMapping("/export")
    public void export(@RequestBody OrgDto orgDto, HttpServletResponse response) {
        ExcelUtil.export(orgDto, response,
                d -> orgMapper.getOrgRecordCount(d),
                d -> orgMapper.getOrgList(orgDto),
                OrgVo.class);
    }
}