package com.incaier.integration.platform.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.constant.BYConstant;
import com.incaier.integration.platform.constant.ErrorCodeConstant;
import com.incaier.integration.platform.entity.Org;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.entity.SysRoleUser;
import com.incaier.integration.platform.entity.doctor.*;
import com.incaier.integration.platform.exception.CommonBusinessException;
import com.incaier.integration.platform.handler.DataJsonSerializer;
import com.incaier.integration.platform.mapper.*;
import com.incaier.integration.platform.request.doctor.DoctorDetailDto;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.response.doctor.DoctorDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorInfoVo;
import com.incaier.integration.platform.response.doctor.DoctorPracticepointVo;
import com.incaier.integration.platform.response.doctor.DoctorVo;
import com.incaier.integration.platform.service.DoctorinfoService;
import com.incaier.integration.platform.util.AesUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 医师基本信息 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
@Service
@DS("testMedicalManage")
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorinfoService {

    private final Logger logger = LoggerFactory.getLogger(DoctorInfoServiceImpl.class);

    @Autowired
    private DoctorInfoMapper doctorInfoMapper;

    @Autowired
    private DoctorPracticepointMapper doctorPracticepointMapper;

    @Autowired
    private DoctorPracticepointItemMapper doctorPracticepointItemMapper;

    @Autowired
    private DoctorQualificationMapper doctorQualificationMapper;

    @Autowired
    private DoctorEducationMapper doctorEducationMapper;

    @Autowired
    private PersonnelMapper personnelMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Value("${personnel.domainId}")
    private String domainId;

    @Override
    public PageInfo<DoctorVo> getDoctorList(DoctorQueryDto dto) {
        PageInfo<DoctorVo> pageInfo;
        Page<DoctorVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<DoctorVo> doctorList = doctorInfoMapper.getDoctorList(dto);
        doctorList.forEach(doctorInfo -> {
            Personnel personnel = personnelMapper.selectOne(Wrappers.<Personnel>lambdaQuery().eq(Personnel::getHealthCareProviderId, doctorInfo.getUserName()).last(BYConstant.SQL_LIMIT_1));
            if (ObjectUtils.isNotEmpty(personnel)) {
                doctorInfo.setLastLoginTime(personnel.getLastLoginTime());
                doctorInfo.setRoles(personnelMapper.getRolesByPersonnelId(personnel.getPk()));
            }
        });
        pageInfo = page.toPageInfo();
        pageInfo.setList(doctorList);
        return pageInfo;
    }

    @Override
    public DoctorDetailVo getDoctorDetail(Integer doctorId) {
        DoctorDetailVo doctorDetailVo = new DoctorDetailVo();
        // 医生基本信息
        getDoctorInfo(doctorId, doctorDetailVo);
        // 教育经历、连续教育、专业学习和培训经历、工作经历、获奖记录
        doctorDetailVo.setDoctorEducation(doctorEducationMapper.getDoctorEducationById(doctorId));
        // 资格信息
        doctorDetailVo.setDoctorQualification(doctorQualificationMapper.getDoctorQualificationById(doctorId));
        // 执业信息
        DoctorPracticepointVo doctorPracticepoint = doctorPracticepointMapper.getDoctorPracticepointById(doctorId);
        doctorDetailVo.setDoctorPracticepoint(doctorPracticepoint);
        // 多机构备案
        if (ObjectUtils.isNotEmpty(doctorPracticepoint) && ObjectUtils.isNotEmpty(doctorPracticepoint.getId())) {
            doctorDetailVo.setDoctorPracticepointItems(doctorPracticepointItemMapper.getDoctorPracticepointItems(doctorPracticepoint.getId()));
        }
        return doctorDetailVo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = RuntimeException.class)
    public Boolean addDoctor(DoctorDetailDto dto) {
        // 添加医生
        Integer doctorId = buildDoctorBasicInfo(dto.getDoctorInfo());
        // 教育经历
        if (ObjectUtils.isNotEmpty(dto.getDoctorEducation())) {
            DoctorEducation doctorEducation = new DoctorEducation();
            BeanUtil.copyProperties(dto.getDoctorEducation(), doctorEducation);
            doctorEducation.setDoctorId(doctorId);
            doctorEducation.setCreateBy(UserHolder.getUserName());
            doctorEducation.setUpdateBy(UserHolder.getUserName());
            doctorEducationMapper.insert(doctorEducation);
        }
        // 资格信息
        if (ObjectUtils.isNotEmpty(dto.getDoctorQualification())) {
            DoctorQualification doctorQualification = new DoctorQualification();
            BeanUtil.copyProperties(dto.getDoctorQualification(), doctorQualification);
            doctorQualification.setDoctorId(doctorId);
            doctorQualification.setCreateBy(UserHolder.getUserName());
            doctorQualification.setUpdateBy(UserHolder.getUserName());
            doctorQualificationMapper.insert(doctorQualification);
        }
        // 执业信息
        if (ObjectUtils.isNotEmpty(dto.getDoctorPracticepoint())) {
            DoctorPracticepoint doctorPracticepoint = new DoctorPracticepoint();
            BeanUtil.copyProperties(dto.getDoctorPracticepoint(), doctorPracticepoint);
            doctorPracticepoint.setDoctorId(doctorId);
            doctorPracticepoint.setCreateBy(UserHolder.getUserName());
            doctorPracticepoint.setUpdateBy(UserHolder.getUserName());
            doctorPracticepointMapper.insert(doctorPracticepoint);
            // 多机构备案
            if (CollectionUtils.isNotEmpty(dto.getDoctorPracticepointItems())) {
                List<DoctorPracticepointItem> items = dto.getDoctorPracticepointItems().stream().map(item -> {
                    DoctorPracticepointItem doctorPracticepointItem = new DoctorPracticepointItem();
                    BeanUtil.copyProperties(item, doctorPracticepointItem);
                    doctorPracticepointItem.setPracticepointId(doctorPracticepoint.getId());
                    doctorPracticepointItem.setCreateBy(UserHolder.getUserName());
                    doctorPracticepointItem.setUpdateBy(UserHolder.getUserName());
                    return doctorPracticepointItem;
                }).collect(Collectors.toList());
                doctorPracticepointItemMapper.saveBatch(items);
            }
        }
        return true;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = RuntimeException.class)
    public DoctorDetailVo deleteDoctor(Integer doctorId) {
        // 删除执业信息
        DoctorPracticepoint doctorPracticepoint = doctorPracticepointMapper.selectOne(Wrappers.<DoctorPracticepoint>lambdaQuery()
                .eq(DoctorPracticepoint::getDoctorId, doctorId)
                .eq(DoctorPracticepoint::getIsDelete, BYConstant.INT_FALSE).last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isNotEmpty(doctorPracticepoint)) {
            // 多机构备案
            doctorPracticepointItemMapper.update(null, Wrappers.<DoctorPracticepointItem>lambdaUpdate()
                    .eq(DoctorPracticepointItem::getPracticepointId, doctorPracticepoint.getId())
                    .eq(DoctorPracticepointItem::getIsDelete, BYConstant.INT_FALSE)
                    .set(DoctorPracticepointItem::getIsDelete, BYConstant.INT_TRUE));
            doctorPracticepointMapper.deleteById(doctorPracticepoint.getId());
        }
        // 删除资格信息
        doctorQualificationMapper.update(null, Wrappers.<DoctorQualification>lambdaUpdate()
                .eq(DoctorQualification::getDoctorId, doctorId)
                .eq(DoctorQualification::getIsDelete, BYConstant.INT_FALSE)
                .set(DoctorQualification::getIsDelete, BYConstant.INT_TRUE));
        // 删除教育经历
        doctorEducationMapper.update(null, Wrappers.<DoctorEducation>lambdaUpdate()
                .eq(DoctorEducation::getDoctorId, doctorId)
                .eq(DoctorEducation::getIsDelete, BYConstant.INT_FALSE)
                .set(DoctorEducation::getIsDelete, BYConstant.INT_TRUE));
        // 删除 personnel 用户数据


        return null;
    }

    /**
     * 建立医生基本信息
     *
     * @param doctorInfoDto request
     * @return {@link Integer}
     */
    private Integer buildDoctorBasicInfo(DoctorInfoDto doctorInfoDto) {
        Personnel personnel = personnelMapper.selectOne(Wrappers.<Personnel>lambdaQuery().eq(Personnel::getHealthCareProviderId, doctorInfoDto.getUserName()).last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isNotEmpty(personnel)) {
            throw new CommonBusinessException("用户名已存在");
        }
        DoctorInfo doctor = doctorInfoMapper.selectOne(Wrappers.<DoctorInfo>lambdaQuery()
                .eq(DoctorInfo::getUserName, doctorInfoDto.getUserName())
                .eq(DoctorInfo::getIsDelete, BYConstant.INT_TRUE)
                .last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isNotEmpty(doctor)) {
            throw new CommonBusinessException("用户名已存在");
        }
        Org org = orgMapper.selectOne(Wrappers.<Org>lambdaQuery().eq(Org::getCode, doctorInfoDto.getOrgCode()));
        if (ObjectUtils.isEmpty(org)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_INVALID_PARAMETER, "机构数据异常");
        }
        DoctorInfo doctorInfo = new DoctorInfo();
        BeanUtil.copyProperties(doctorInfoDto, doctorInfo);
        doctorInfo.setCreateBy(UserHolder.getUserName());
        doctorInfo.setUpdateBy(UserHolder.getUserName());
        doctorInfoMapper.insert(doctorInfo);
        logger.info("新建医生，id：{}，工号：{}", doctorInfo.getId(), doctorInfo.getUserName());
        // 新建 personnel & role
        Personnel newPersonnel = Personnel.builder()
                .domainId(domainId)
                .healthCareProviderId(doctorInfo.getUserName())
                .passWord(AesUtil.encrypt(doctorInfo.getUserName()))
                .phone(doctorInfo.getPhoneNumber())
                .identityNo(doctorInfo.getIdentityNo())
                .name(doctorInfo.getName())
                .genderId(doctorInfoDto.getSex().toString())
                .genderDepict(DataJsonSerializer.GenderSerializer.GENDER_MAP.getOrDefault(doctorInfoDto.getSex().toString(), "未知"))
                .dateOfBirth(doctorInfo.getBirthday().toString())
                .email(doctorInfo.getEmail())
                .createTime(LocalDateTime.now())
                .build();
        personnelMapper.insert(newPersonnel);
        logger.info("新建personnel，id：{}", newPersonnel.getPk());
        List<SysRoleUser> roles = doctorInfoDto.getRoles().stream().map(roleVO -> SysRoleUser.builder()
                .roleId(Integer.valueOf(roleVO.getRoleId()))
                .userId(newPersonnel.getPk().intValue()).build()).collect(Collectors.toList());
        sysRoleUserMapper.saveBatch(roles);
        return doctorInfo.getId();
    }

    /**
     * 获取医生信息
     *
     * @param doctorId       医生id
     * @param doctorDetailVo 医生详细信息vo
     */
    private void getDoctorInfo(Integer doctorId, DoctorDetailVo doctorDetailVo) {
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(doctorId);
        if (ObjectUtils.isNotEmpty(doctorInfo)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_INVALID_PARAMETER, "医生数据异常");
        }
        DoctorInfoVo doctorInfoVo = new DoctorInfoVo();
        BeanUtil.copyProperties(doctorInfo, doctorInfoVo);
        // 获取用户角色
        Personnel personnel = personnelMapper.selectOne(Wrappers.<Personnel>lambdaQuery().eq(Personnel::getHealthCareProviderId, doctorInfo.getUserName()).last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isNotEmpty(personnel)) {
            doctorInfoVo.setRoles(personnelMapper.getRolesByPersonnelId(personnel.getPk()));
        }
        doctorDetailVo.setDoctorInfo(doctorInfoVo);
    }
}
