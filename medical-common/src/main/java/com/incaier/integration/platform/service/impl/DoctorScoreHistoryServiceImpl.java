package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.constant.ErrorCodeConstant;
import com.incaier.integration.platform.entity.doctor.DoctorInfo;
import com.incaier.integration.platform.entity.doctor.DoctorScoreHistory;
import com.incaier.integration.platform.enums.DoctorScoreEventType;
import com.incaier.integration.platform.exception.CommonBusinessException;
import com.incaier.integration.platform.mapper.DoctorInfoMapper;
import com.incaier.integration.platform.mapper.DoctorPracticepointMapper;
import com.incaier.integration.platform.mapper.DoctorScoreHistoryMapper;
import com.incaier.integration.platform.mapper.OrgMapper;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.request.doctor.DoctorScoreHistoryDto;
import com.incaier.integration.platform.response.doctor.DoctorInfoVo;
import com.incaier.integration.platform.response.doctor.DoctorPracticepointVo;
import com.incaier.integration.platform.response.doctor.DoctorScoreHistoryDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorScoreVo;
import com.incaier.integration.platform.service.DoctorScoreHistoryService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 医生评分历史 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
@Service
@DS("byIntegrationPlatform")
public class DoctorScoreHistoryServiceImpl extends ServiceImpl<DoctorScoreHistoryMapper, DoctorScoreHistory> implements DoctorScoreHistoryService {

    @Autowired
    private DoctorInfoMapper doctorInfoMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private DoctorPracticepointMapper doctorPracticepointMapper;
    @Autowired
    private DoctorScoreHistoryMapper doctorScoreHistoryMapper;

    @Override
    public PageInfo<DoctorScoreVo> getDoctorScoreList(DoctorQueryDto dto) {
        PageInfo<DoctorScoreVo> pageInfo;
        Page<DoctorScoreVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<DoctorScoreVo> doctorList = buildDoctorScoreList(dto);
        pageInfo = page.toPageInfo();
        pageInfo.setList(doctorList);
        return pageInfo;
    }

    @Override
    public List<DoctorScoreVo> buildDoctorScoreList(DoctorQueryDto dto) {
        List<DoctorScoreVo> doctorList = doctorInfoMapper.getDoctorScoreList(dto);
        doctorList.forEach(doctorInfo -> {
            DoctorPracticepointVo doctorPracticepoint = doctorPracticepointMapper.getDoctorPraTypeLevelById(doctorInfo.getDoctorId());
            if (ObjectUtils.isNotEmpty(doctorPracticepoint)) {
                doctorInfo.setPracticeLevel(doctorPracticepoint.getPracticeLevel());
                doctorInfo.setPracticeType(doctorPracticepoint.getPracticeType());
            }
        });
        return doctorList;
    }

    @Override
    public DoctorScoreHistoryDetailVo getScoreHistoryDetail(Integer doctorId) {
        DoctorScoreHistoryDetailVo response = new DoctorScoreHistoryDetailVo();
        DoctorInfoVo doctorInfo = doctorInfoMapper.getDoctorInfoById(doctorId);
        if (ObjectUtils.isEmpty(doctorInfo)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "医生数据异常");
        }
        response.setDoctorInfo(doctorInfo);
        response.setHistoryList(doctorScoreHistoryMapper.getDoctorSocreHistoryDetail(doctorId));
        return response;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public Boolean grade(DoctorScoreHistoryDto dto) {
        // 更新分数
        DoctorScoreEventType eventType = DoctorScoreEventType.fromValue(dto.getEventType());
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(dto.getDoctorId());
        if (ObjectUtils.isEmpty(doctorInfo)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "用户数据异常");
        }
        int currentScore = doctorInfo.getScore();
        currentScore = eventType.apply(currentScore, dto.getScore());
        doctorInfo.setScore(currentScore);
        doctorInfo.setUpdateBy(UserHolder.getUserName());
        doctorInfoMapper.updateById(doctorInfo);
        // 插入历史
        DoctorScoreHistory doctorScoreHistory = new DoctorScoreHistory();
        BeanUtils.copyProperties(dto, doctorScoreHistory);
        doctorScoreHistory.setCreateBy(UserHolder.getUserName());
        doctorScoreHistoryMapper.insert(doctorScoreHistory);
        return true;
    }
}
