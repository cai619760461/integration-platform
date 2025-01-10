package com.incaier.integration.platform.service.impl;

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
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.incaier.integration.platform.constant.BYConstant.SQL_LIMIT_1;

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
        if (ObjectUtils.isNotEmpty(before)) {
//            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "数据异常");
            response.put("before", transferBean(before));
        }
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
}