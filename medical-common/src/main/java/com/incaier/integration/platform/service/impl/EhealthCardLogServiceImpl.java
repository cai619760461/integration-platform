package com.incaier.integration.platform.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.constant.ErrorCodeConstant;
import com.incaier.integration.platform.entity.EhealthCardLog;
import com.incaier.integration.platform.exception.CommonBusinessException;
import com.incaier.integration.platform.mapper.EhealthCardLogMapper;
import com.incaier.integration.platform.request.EhealthCardDto;
import com.incaier.integration.platform.response.health.EhealthCardRecordInfoVo;
import com.incaier.integration.platform.response.health.EhealthCardRecordVo;
import com.incaier.integration.platform.service.EhealthCardLogService;
import com.incaier.integration.platform.util.IdCardUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.incaier.integration.platform.constant.BYConstant.*;

/**
 * 健康卡操作日志实现类
 *
 * @author weijie.cai
 * @description 用户电子健康卡变更日志
 * @date 2024-06-04
 */
@Service
public class EhealthCardLogServiceImpl extends ServiceImpl<EhealthCardLogMapper, EhealthCardLog> implements EhealthCardLogService {

    private final Logger logger = LoggerFactory.getLogger(EhealthCardLogServiceImpl.class);

    @Resource
    private EhealthCardLogMapper ehealthCardLogMapper;

    @Override
    public PageInfo<EhealthCardRecordVo> getCardRecord(EhealthCardDto dto) {
        PageInfo<EhealthCardRecordVo> pageInfo;
        Page<EhealthCardRecordVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<EhealthCardRecordVo> applicationRecordList = ehealthCardLogMapper.getCardRecord(dto);
        pageInfo = page.toPageInfo();
        pageInfo.setList(applicationRecordList);
        return pageInfo;
    }

    @Override
    public Map<String, EhealthCardRecordInfoVo> getUpdateInfo(Integer id) {
        HashMap<String, EhealthCardRecordInfoVo> response = new HashMap<>();
        EhealthCardLog after = ehealthCardLogMapper.selectById(id);
        if (ObjectUtils.isEmpty(after)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "数据异常");
        }
        response.put("after", transferBean(after));
        EhealthCardLog before = ehealthCardLogMapper.selectOne(Wrappers.<EhealthCardLog>lambdaQuery()
                .eq(EhealthCardLog::getCardNo, after.getCardNo())
                .lt(EhealthCardLog::getId, id)
                .orderByDesc(EhealthCardLog::getId)
                .last(SQL_LIMIT_1));
        if (ObjectUtils.isEmpty(before)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "数据异常");
        }
        response.put("before", transferBean(before));
        return response;
    }

    /**
     * bean 转换
     *
     * @param ehealthCardLog ehealth 申领流水
     * @return {@link EhealthCardRecordVo}
     */
    private EhealthCardRecordInfoVo transferBean(EhealthCardLog ehealthCardLog) {
        EhealthCardRecordInfoVo ehealthCardRecordInfoVo = new EhealthCardRecordInfoVo();
        BeanUtils.copyProperties(ehealthCardLog, ehealthCardRecordInfoVo);
        // 填写生日
        ehealthCardRecordInfoVo.setBirthday(IdCardUtil.getBirthDate(ehealthCardLog.getIdNo()));
        return ehealthCardRecordInfoVo;
    }

    @Override
    public void export(EhealthCardDto dto, HttpServletResponse response) {
        logger.info("************* 申领记录列表导出开始，操作行为：{} **************", dto.getOperateType());
        //文件名
        String fileName = "Export_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        try (OutputStream outputStream = response.getOutputStream()) {
            // 获取导出总数
            int count = ehealthCardLogMapper.getCardRecordCount(dto);
            // 定义首表、单次任务插入条数、获取分页总数
            int sheetNo = 1, pageCount = count % EXCEL_INSERT_NUM == 0 ? count / EXCEL_INSERT_NUM : count / EXCEL_INSERT_NUM + 1;
            // 初始化 ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(outputStream)
                    .head(EhealthCardRecordVo.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();
            // 创建首表 WriteSheet
            String sheetName = EXCEL_SHEET_PRFIX + sheetNo;
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, sheetName).build();
            // 记录上一次任务最大 id
            Long lastId = null;
            //写入每一页分页查询的数据
            for (int i = 1; i <= pageCount; i++) {
                // 首页直接进行分页查询，，反之基于上一次分页查询的分页定位实际偏移量，筛选前n条数据
                PageHelper.startPage(i == 1 ? i : 0, EXCEL_INSERT_NUM, false);
                dto.setLastId(lastId);
                List<EhealthCardRecordVo> records = ehealthCardLogMapper.getCardRecord(dto);
                //更新下一次分页查询用的id
                if (CollectionUtils.isNotEmpty(records)) lastId = records.get(records.size() - 1).getId();
                // 写入表格
                excelWriter.write(records, writeSheet);
                // 是否达到单表最大数据量，达到切换新表
                if ((i * EXCEL_INSERT_NUM) % EXCEL_SHEET_MAX == 0) {
                    sheetName = EXCEL_SHEET_PRFIX + (++sheetNo);
                    writeSheet = EasyExcel.writerSheet(sheetNo, sheetName).build();
                }
            }
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            excelWriter.finish();
            outputStream.flush();
            outputStream.close();
            logger.info("*********** 申领记录列表导出结束 **************");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}