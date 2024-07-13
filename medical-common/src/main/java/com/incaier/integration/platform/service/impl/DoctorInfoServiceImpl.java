package com.incaier.integration.platform.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
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
import com.incaier.integration.platform.request.BaseDto;
import com.incaier.integration.platform.request.doctor.DoctorDetailDto;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.request.doctor.ExpertLabelDto;
import com.incaier.integration.platform.request.excel.ExcelDoctorEntity;
import com.incaier.integration.platform.response.RoleVO;
import com.incaier.integration.platform.response.doctor.*;
import com.incaier.integration.platform.service.DoctorInfoService;
import com.incaier.integration.platform.util.AesUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
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
@DS("byIntegrationPlatform")
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorInfoService {

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

    @Autowired
    private ExpertLabelMapper expertLabelMapper;

    @Override
    public PageInfo<DoctorVo> getDoctorList(DoctorQueryDto dto) {
        PageInfo<DoctorVo> pageInfo;
        Page<DoctorVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<DoctorVo> doctorList = doctorInfoMapper.getDoctorList(dto);
        doctorList.forEach(doctorInfo -> {
            // 角色信息
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
        buildDoctorInfo(doctorId, doctorDetailVo);
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
    @DSTransactional
    public Boolean addDoctor(DoctorDetailDto dto) {
        // 添加医生
        Integer doctorId = buildDoctorBasicInfo(dto.getDoctorInfo());
        // 教育经历
        saveOrUpdateDoctorRelation(doctorId, dto.getDoctorEducation(), x -> doctorEducationMapper.saveOrUpdate(x));
        // 资格信息
        saveOrUpdateDoctorRelation(doctorId, dto.getDoctorQualification(), x -> doctorQualificationMapper.saveOrUpdate(x));
        // 执业信息
        saveOrUpdateDoctorRelation(doctorId, dto.getDoctorPracticepoint(), x -> {
            doctorPracticepointMapper.saveOrUpdate(x);
            Integer practicepointId = dto.getDoctorPracticepoint().getId();
            // 多机构备案
            if (CollectionUtils.isNotEmpty(dto.getDoctorPracticepointItems())) {
                dto.getDoctorPracticepointItems().forEach(item -> {
                    item.setPracticepointId(practicepointId);
                    saveOrUpdateDoctorRelation(0, item, itemDto -> doctorPracticepointItemMapper.saveOrUpdate(itemDto));
                });
            }
        });
        return true;
    }

    @Override
    @DSTransactional
    public Boolean updateDetail(DoctorDetailDto dto) {
        // 更新用户、角色、基本信息
        // TODO personnel & doctor_info 暂不进行双向修改，制作新增
        DoctorInfoDto doctorInfo = dto.getDoctorInfo();
        logger.info("更新医生信息，医生id：{}", doctorInfo.getId());
        Personnel personnel = personnelMapper.selectOne(Wrappers.<Personnel>lambdaQuery()
                .eq(Personnel::getHealthCareProviderId, doctorInfo.getUserName())
                .last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isEmpty(personnel)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "用户数据异常");
        }
        // 更新角色
        saveRoles(dto.getDoctorInfo().getRoles(), personnel.getPk().intValue());
        // 更新医生数据
        saveOrUpdateDoctorRelation(doctorInfo.getId(), dto.getDoctorInfo(), x -> doctorInfoMapper.saveOrUpdate(x));
        // 专家信息
        handlerExpertInfo(doctorInfo);
        // 教育经历
        saveOrUpdateDoctorRelation(doctorInfo.getId(), dto.getDoctorEducation(), x -> doctorEducationMapper.saveOrUpdate(x));
        // 资格信息
        saveOrUpdateDoctorRelation(doctorInfo.getId(), dto.getDoctorQualification(), x -> doctorQualificationMapper.saveOrUpdate(x));
        // 执业信息
        saveOrUpdateDoctorRelation(doctorInfo.getId(), dto.getDoctorPracticepoint(), x -> {
            doctorPracticepointMapper.saveOrUpdate(x);
            Integer practicepointId = dto.getDoctorPracticepoint().getId();
            // 多机构备案
            if (CollectionUtils.isNotEmpty(dto.getDoctorPracticepointItems())) {
                dto.getDoctorPracticepointItems().forEach(item -> {
                    item.setPracticepointId(practicepointId);
                    saveOrUpdateDoctorRelation(doctorInfo.getId(), item, itemDto -> doctorPracticepointItemMapper.saveOrUpdate(itemDto));
                });
            }
        });
        // 删除多机构备案
        if (CollectionUtils.isNotEmpty(dto.getDeleteIds())) {
            doctorPracticepointItemMapper.update(null, Wrappers.<DoctorPracticepointItem>lambdaUpdate()
                    .in(DoctorPracticepointItem::getId, dto.getDeleteIds())
                    .set(DoctorPracticepointItem::getIsDelete, BYConstant.INT_TRUE));
        }
        return true;
    }

    /**
     * 保存或更新医生相关信息
     *
     * @param doctorId 医生 id
     * @param dto      request
     * @param consumer consumer
     */
    public <D extends BaseDto> void saveOrUpdateDoctorRelation(Integer doctorId, D dto, Consumer<D> consumer) {
        if (ObjectUtils.isEmpty(doctorId) || ObjectUtils.isEmpty(dto)) {
            return;
        }
        dto.setDoctorId(doctorId);
        dto.setCreateBy(UserHolder.getUserName());
        dto.setUpdateBy(UserHolder.getUserName());
        consumer.accept(dto);
    }

    @Override
    @DSTransactional
    public Boolean deleteDoctor(Integer doctorId) {
        // 删除执业信息
        DoctorPracticepoint doctorPracticepoint = doctorPracticepointMapper.selectOne(Wrappers.<DoctorPracticepoint>lambdaQuery().eq(DoctorPracticepoint::getDoctorId, doctorId).eq(DoctorPracticepoint::getIsDelete, BYConstant.INT_FALSE).last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isNotEmpty(doctorPracticepoint)) {
            // 多机构备案
            doctorPracticepointItemMapper.update(null, Wrappers.<DoctorPracticepointItem>lambdaUpdate().eq(DoctorPracticepointItem::getPracticepointId, doctorPracticepoint.getId()).eq(DoctorPracticepointItem::getIsDelete, BYConstant.INT_FALSE).set(DoctorPracticepointItem::getIsDelete, BYConstant.INT_TRUE));
            doctorPracticepoint.setIsDelete(BYConstant.INT_TRUE);
            doctorPracticepointMapper.updateById(doctorPracticepoint);
        }
        // 删除资格信息
        doctorQualificationMapper.update(null, Wrappers.<DoctorQualification>lambdaUpdate().eq(DoctorQualification::getDoctorId, doctorId).eq(DoctorQualification::getIsDelete, BYConstant.INT_FALSE).set(DoctorQualification::getIsDelete, BYConstant.INT_TRUE));
        // 删除教育经历
        doctorEducationMapper.update(null, Wrappers.<DoctorEducation>lambdaUpdate().eq(DoctorEducation::getDoctorId, doctorId).eq(DoctorEducation::getIsDelete, BYConstant.INT_FALSE).set(DoctorEducation::getIsDelete, BYConstant.INT_TRUE));
        // 删除 doctor & personnel 用户数据
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(doctorId);
        if (ObjectUtils.isEmpty(doctorInfo)) {
            logger.error("doctorInfo is null, doctorId:{}", doctorId);
            return true;
        }
        Personnel personnel = personnelMapper.selectOne(Wrappers.<Personnel>lambdaQuery().eq(Personnel::getHealthCareProviderId, doctorInfo.getUserName()));
        // 删除已有角色数据
        if (ObjectUtils.isNotEmpty(personnel)) {
            sysRoleUserMapper.delete(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getUserId, personnel.getPk()));
            // 删除用户数据
            personnelMapper.deleteById(personnel.getPk());
        }
        // 删除医生数据
        doctorInfo.setIsDelete(BYConstant.INT_TRUE);
        doctorInfoMapper.updateById(doctorInfo);
        return true;
    }

    /**
     * 建立医生基本信息
     *
     * @param doctorInfoDto request
     * @return {@link Integer}
     */
    @Override
    @DSTransactional
    public Integer buildDoctorBasicInfo(DoctorInfoDto doctorInfoDto) {
        Personnel personnel = personnelMapper.selectOne(Wrappers.<Personnel>lambdaQuery()
                .eq(Personnel::getHealthCareProviderId, doctorInfoDto.getUserName())
                .and(wrap -> wrap.eq(Personnel::getDeleteFlag, "0").or().isNull(Personnel::getDeleteFlag))
                .last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isNotEmpty(personnel)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_INVALID_PARAMETER, "用户名已存在");
        }
        DoctorInfo doctor = doctorInfoMapper.selectOne(Wrappers.<DoctorInfo>lambdaQuery()
                .eq(DoctorInfo::getUserName, doctorInfoDto.getUserName())
                .eq(DoctorInfo::getIsDelete, BYConstant.INT_TRUE)
                .last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isNotEmpty(doctor)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_INVALID_PARAMETER, "用户名已存在");
        }
        Org org = orgMapper.selectOne(Wrappers.<Org>lambdaQuery()
                .eq(Org::getCode, doctorInfoDto.getOrgCode())
                .eq(Org::getIsDelete, BYConstant.INT_FALSE)
                .last(BYConstant.SQL_LIMIT_1));
        if (ObjectUtils.isEmpty(org)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_INVALID_PARAMETER, "机构数据异常");
        }
        saveOrUpdateDoctorRelation(0, doctorInfoDto, x -> doctorInfoMapper.saveOrUpdate(x));
        logger.info("新建医生，id：{}，工号：{}", doctorInfoDto.getId(), doctorInfoDto.getUserName());
        // 更新专家信息
        handlerExpertInfo(doctorInfoDto);
        // 新建 personnel & role
        saveRoles(doctorInfoDto.getRoles(), savePersonnel(doctorInfoDto));
        return doctorInfoDto.getId();
    }

    /**
     * 处理专家信息
     *
     * @param doctorInfoDto 医生信息dto
     */
    private void handlerExpertInfo(DoctorInfoDto doctorInfoDto) {
        if (BYConstant.INT_FALSE.equals(doctorInfoDto.getIsExpert())) {
            expertLabelMapper.update(null, Wrappers.<ExpertLabel>lambdaUpdate()
                    .eq(ExpertLabel::getDoctorId, doctorInfoDto.getId())
                    .set(ExpertLabel::getIsDelete, BYConstant.INT_TRUE));
        }else {
            Set<Integer> removeDictIds;
            Set<Integer> originDictIds = expertLabelMapper.getDoctorLabelDictIds(doctorInfoDto.getDoctorId());
            if (CollectionUtils.isNotEmpty(doctorInfoDto.getExpertLabels())) {
                Set<Integer> newDictIds = doctorInfoDto.getExpertLabels().stream().map(ExpertLabelDto::getDictId).collect(Collectors.toSet());
                removeDictIds = getDiffrent(originDictIds, newDictIds);
                Set<Integer> addDictIds = getDiffrent(newDictIds, originDictIds);
                logger.info("addDictIds:{}", addDictIds);
                if (CollectionUtils.isNotEmpty(addDictIds)) {
                    addDictIds.forEach(dictId -> saveOrUpdateDoctorRelation(doctorInfoDto.getId(), new ExpertLabelDto(dictId), x -> expertLabelMapper.saveOrUpdate(x)));
                }
            }else {
                removeDictIds = originDictIds;
            }
            if (CollectionUtils.isNotEmpty(removeDictIds)){
                logger.info("removeDictIds:{}", removeDictIds);
                expertLabelMapper.update(null, Wrappers.<ExpertLabel>lambdaUpdate()
                        .eq(ExpertLabel::getDoctorId, doctorInfoDto.getId())
                        .in(ExpertLabel::getDictId, removeDictIds)
                        .set(ExpertLabel::getIsDelete, BYConstant.INT_TRUE));
            }
        }
    }

    private static Set<Integer> getDiffrent(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.removeAll(set2);
        return intersection;
    }

    /**
     * 保存人员信息
     *
     * @param doctorInfoDto 医生信息dto
     * @return {@link Integer} 人员 pk
     */
    public Integer savePersonnel(DoctorInfoDto doctorInfoDto) {
        Personnel newPersonnel = Personnel.builder()
                .domainId(StringUtils.isEmpty(doctorInfoDto.getDomainId()) ? domainId : doctorInfoDto.getDomainId())
                .healthCareProviderId(doctorInfoDto.getUserName())
                .passWord(AesUtil.encrypt(doctorInfoDto.getUserName()))
                .phone(doctorInfoDto.getPhoneNumber())
                .identityNo(doctorInfoDto.getIdentityNo())
                .name(doctorInfoDto.getName())
                .genderId(doctorInfoDto.getSex().toString())
                .genderDepict(DataJsonSerializer.GenderSerializer.GENDER_MAP.getOrDefault(doctorInfoDto.getSex().toString(), "未知"))
                .dateOfBirth(doctorInfoDto.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .email(doctorInfoDto.getEmail())
                .createTime(LocalDateTime.now())
                .build();
        personnelMapper.insert(newPersonnel);
        logger.info("新建 personnel，id：{}", newPersonnel.getPk());
        return newPersonnel.getPk().intValue();
    }

    /**
     * 保存角色
     *
     * @param roleVos 角色列表
     * @param pk      personnel pk
     */
    public void saveRoles(List<RoleVO> roleVos, Integer pk) {
        // 删除已有角色数据
        sysRoleUserMapper.delete(Wrappers.<SysRoleUser>lambdaQuery().eq(SysRoleUser::getUserId, pk));
        if (CollectionUtils.isEmpty(roleVos)) {
            return;
        }
        List<SysRoleUser> roles = roleVos.stream().map(roleVO -> SysRoleUser.builder()
                .roleId(Integer.valueOf(roleVO.getRoleId()))
                .userId(pk)
                .build())
                .collect(Collectors.toList());
        // 保存角色关系
        sysRoleUserMapper.saveBatch(roles);
    }

    /**
     * 获取医生信息
     *
     * @param doctorId       医生id
     * @param doctorDetailVo 医生详细信息vo
     */
    private void buildDoctorInfo(Integer doctorId, DoctorDetailVo doctorDetailVo) {
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(doctorId);
        if (ObjectUtils.isEmpty(doctorInfo) || BYConstant.INT_TRUE.equals(doctorInfo.getIsDelete())) {
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
        // 专家信息
        doctorInfoVo.setIsExpert(doctorInfo.getIsExpert());
        if (BYConstant.INT_FALSE.equals(doctorInfoVo.getIsExpert())) {
            return;
        }
        doctorInfoVo.setExpertLabels(expertLabelMapper.getDoctorsLabels(doctorId));
    }

    @Override
    public List<ExcelDoctorEntity> getUserTemplate() {
        ExcelDoctorEntity entity = ExcelDoctorEntity.builder()
                .domainId(domainId)
                .userName("system")
                .phoneNumber("13555555555")
                .identityNo("340881195603255914")
                .birthday(LocalDate.parse("1956-03-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .name("胡星")
                .sex(0)
                .roleIds("1,2,10")
                .orgCode("620000000758")
                .email("wiggjxlt@qq.com")
                .build();
        return Collections.singletonList(entity);
    }
}
