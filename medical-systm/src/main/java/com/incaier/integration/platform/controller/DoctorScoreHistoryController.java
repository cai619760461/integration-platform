package com.incaier.integration.platform.controller;

import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.mapper.DoctorInfoMapper;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.request.doctor.DoctorScoreHistoryDto;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.doctor.DoctorScoreHistoryDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorScoreVo;
import com.incaier.integration.platform.service.DoctorScoreHistoryService;
import com.incaier.integration.platform.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 医生评分历史
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
@RestController
@RequestMapping("/score")
public class DoctorScoreHistoryController {

    @Autowired
    private DoctorScoreHistoryService doctorscorehistoryService;

    @Autowired
    private DoctorInfoMapper doctorInfoMapper;

    /**
     * 获取医生评分表
     *
     * @param queryDto 查询dto
     * @return {@link Result}<{@link PageInfo}<{@link DoctorScoreVo}>>
     */
    @PostMapping(value = "/list")
    public Result<PageInfo<DoctorScoreVo>> getDoctorScoreList(@RequestBody DoctorQueryDto queryDto) {
        return Result.success(doctorscorehistoryService.getDoctorScoreList(queryDto));
    }

    /**
     * 获取某个医生评分历史
     *
     * @param doctorId 医生id
     * @return {@link Result}<{@link DoctorScoreHistoryDetailVo}>
     */
    @GetMapping(value = "/detail")
    public Result<DoctorScoreHistoryDetailVo> getScoreHistoryDetail(@RequestParam("doctorId") Integer doctorId) {
        return Result.success(doctorscorehistoryService.getScoreHistoryDetail(doctorId));
    }

    /**
     * 打分
     *
     * @param dto request
     * @return {@link Result}<{@link DoctorScoreHistoryDetailVo}>
     */
    @PostMapping(value = "/grade")
    public Result<Boolean> grade(@Validated @RequestBody DoctorScoreHistoryDto dto) {
        return Result.success(doctorscorehistoryService.grade(dto));
    }

    /**
     * 导出医生评分列表
     *
     * @param queryDto 查询dto
     */
    @PostMapping(value = "/export")
    public void export(@RequestBody DoctorQueryDto queryDto, HttpServletResponse response) {
        ExcelUtil.export(queryDto, response,
                d -> doctorInfoMapper.getScoreHistoryCount(queryDto),
                d -> doctorscorehistoryService.buildDoctorScoreList(queryDto),
                DoctorScoreVo.class);
    }
}
