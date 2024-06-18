package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.config.MinioPropertiesConfig;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.constant.BYConstant;
import com.incaier.integration.platform.constant.ErrorCodeConstant;
import com.incaier.integration.platform.entity.equipment.MedicalEquipment;
import com.incaier.integration.platform.entity.equipment.MedicalEquipmentFile;
import com.incaier.integration.platform.exception.CommonBusinessException;
import com.incaier.integration.platform.mapper.MedicalEquipmentFileMapper;
import com.incaier.integration.platform.mapper.MedicalEquipmentMapper;
import com.incaier.integration.platform.request.MedicalEquipmentDto;
import com.incaier.integration.platform.request.MedicalEquipmentQueryDto;
import com.incaier.integration.platform.response.MedicalEquipmentVo;
import com.incaier.integration.platform.service.MedicalequipmentService;
import com.incaier.integration.platform.service.MedicalequipmentfileService;
import com.incaier.integration.platform.util.MinioUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 医疗设备管理 服务实现类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-18
 */
@Service
@DS("testMedicalManage")
public class MedicalEquipmentServiceImpl extends ServiceImpl<MedicalEquipmentMapper, MedicalEquipment> implements MedicalequipmentService {

    @Autowired
    private MedicalequipmentfileService medicalequipmentfileService;

    @Autowired
    private MedicalEquipmentMapper medicalEquipmentMapper;

    @Autowired
    private MedicalEquipmentFileMapper medicalEquipmentFileMapper;

    @Resource
    private MinioUtils minioUtils;

    @Resource
    private MinioPropertiesConfig minioPropertiesConfig;

    @Override
    public PageInfo<MedicalEquipmentVo> getList(MedicalEquipmentQueryDto dto) {
        PageInfo<MedicalEquipmentVo> pageInfo;
        Page<MedicalEquipmentVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<MedicalEquipmentVo> dictTypeList = medicalEquipmentMapper.getList(dto);
        pageInfo = page.toPageInfo();
        pageInfo.setList(dictTypeList);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public Boolean saveOrUpdateEquipment(MedicalEquipmentDto medicalEquipmentDto) {
        // 基本信息修改

        if (ObjectUtils.isEmpty(medicalEquipmentDto.getId())) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "数据异常");
        }
        // 修改附件
        // 新增附件
        if (CollectionUtils.isNotEmpty(medicalEquipmentDto.getAddAnnex())) {
            List<MedicalEquipmentFile> files = medicalEquipmentDto.getAddAnnex().stream().map(x -> {
                MedicalEquipmentFile medicalEquipmentFile = new MedicalEquipmentFile();
                BeanUtils.copyProperties(x, medicalEquipmentFile);
                medicalEquipmentFile.setEquipmentId(medicalEquipmentDto.getId());
                medicalEquipmentFile.setCreateBy(UserHolder.getUserName());
                medicalEquipmentFile.setUpdateBy(UserHolder.getUserName());
                return medicalEquipmentFile;
            }).collect(Collectors.toList());
            medicalequipmentfileService.saveBatch(files);
        }
        // 删除附件信息
        if (CollectionUtils.isNotEmpty(medicalEquipmentDto.getDeleteIds())) {
            List<MedicalEquipmentFile> files = medicalEquipmentFileMapper.selectBatchIds(medicalEquipmentDto.getDeleteIds());
            medicalEquipmentFileMapper.update(null, Wrappers.<MedicalEquipmentFile>lambdaUpdate()
                    .in(MedicalEquipmentFile::getId, medicalEquipmentDto.getDeleteIds())
                    .set(MedicalEquipmentFile::getIsDelete, BYConstant.INT_TRUE));
            minioUtils.removeFiles(minioPropertiesConfig.getBucketName(), files.stream().map(MedicalEquipmentFile::getFilePath).collect(Collectors.toList()));
        }
        return null;
    }
}
