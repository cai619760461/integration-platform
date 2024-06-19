package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.doctor.ExpertLabel;
import com.incaier.integration.platform.mapper.DoctorInfoMapper;
import com.incaier.integration.platform.mapper.DoctorPracticepointMapper;
import com.incaier.integration.platform.mapper.ExpertLabelMapper;
import com.incaier.integration.platform.request.doctor.ExpertLabelQueryDto;
import com.incaier.integration.platform.response.doctor.DoctorExpertLabelVo;
import com.incaier.integration.platform.response.doctor.DoctorPracticepointVo;
import com.incaier.integration.platform.service.ExpertLabelService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 专家标签 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-19
 */
@Service
@DS("testMedicalManage")
public class ExpertLabelServiceImpl extends ServiceImpl<ExpertLabelMapper, ExpertLabel> implements ExpertLabelService {

    @Autowired
    private ExpertLabelMapper expertLabelMapper;

    @Autowired
    private DoctorInfoMapper doctorInfoMapper;

    @Autowired
    private DoctorPracticepointMapper doctorPracticepointMapper;

    @Override
    public PageInfo<DoctorExpertLabelVo> getExpertList(ExpertLabelQueryDto dto) {
        PageInfo<DoctorExpertLabelVo> pageInfo;
        Page<DoctorExpertLabelVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<DoctorExpertLabelVo> expertList = buildExpertList(dto);
        pageInfo = page.toPageInfo();
        pageInfo.setList(expertList);
        return pageInfo;
    }

    @Override
    public List<DoctorExpertLabelVo> buildExpertList(ExpertLabelQueryDto dto) {
        List<DoctorExpertLabelVo> expertList = doctorInfoMapper.getDoctorExpertList(dto);
        expertList.forEach(expert -> {
            DoctorPracticepointVo doctorPracticepoint = doctorPracticepointMapper.getDoctorPracticepointById(expert.getId());
            if (ObjectUtils.isNotEmpty(doctorPracticepoint)) {
                expert.setPracticeLevel(doctorPracticepoint.getPracticeLevel());
                expert.setPracticeType(doctorPracticepoint.getPracticeType());
            }
            expert.setExpertLabels(expertLabelMapper.getDoctorsLabels(expert.getId()));
        });
        return expertList;
    }
}
