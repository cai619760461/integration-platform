package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.mapper.DoctorInfoMapper;
import com.incaier.integration.platform.request.doctor.ExpertLabelQueryDto;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.doctor.DoctorExpertLabelVo;
import com.incaier.integration.platform.service.ExpertLabelService;
import com.incaier.integration.platform.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 专家标签
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
@RestController
@RequestMapping("/expert")
public class ExpertLabelController {

    @Autowired
    private ExpertLabelService expertlabelService;

    @Autowired
    private DoctorInfoMapper doctorInfoMapper;

    /**
     * 获取专家列表
     *
     * @param queryDto 查询dto
     * @return {@link Result}<{@link PageInfo}<{@link DoctorExpertLabelVo}>>
     */
    @PostMapping(value = "/list")
    public Result<PageInfo<DoctorExpertLabelVo>> getExpertList(@RequestBody ExpertLabelQueryDto queryDto) {
        return Result.success(expertlabelService.getExpertList(queryDto));
    }

    /**
     * 导出专家列表
     *
     * @param queryDto 查询dto
     */
    @PostMapping(value = "/export")
    public void export(@RequestBody ExpertLabelQueryDto queryDto, HttpServletResponse response) {
        ExcelUtil.export(queryDto, response,
                d -> doctorInfoMapper.getExpertCount(queryDto),
                d -> expertlabelService.buildExpertList(queryDto),
                DoctorExpertLabelVo.class);
    }
}
