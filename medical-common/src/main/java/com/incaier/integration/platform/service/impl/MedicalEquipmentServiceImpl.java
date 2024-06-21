package com.incaier.integration.platform.service.impl;

import cn.hutool.core.convert.Convert;
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
import com.incaier.integration.platform.response.MedicalEquipmentDetailVo;
import com.incaier.integration.platform.response.MedicalEquipmentFileVo;
import com.incaier.integration.platform.response.MedicalEquipmentVo;
import com.incaier.integration.platform.service.MedicalEquipmentService;
import com.incaier.integration.platform.service.MedicalEquipmentFileService;
import com.incaier.integration.platform.util.MinioUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MedicalEquipmentServiceImpl extends ServiceImpl<MedicalEquipmentMapper, MedicalEquipment> implements MedicalEquipmentService {

    private final Logger logger = LoggerFactory.getLogger(MedicalEquipmentServiceImpl.class);

    @Autowired
    private MedicalEquipmentFileService medicalequipmentfileService;

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
        MedicalEquipment medicalEquipment = new MedicalEquipment();
        BeanUtils.copyProperties(medicalEquipmentDto, medicalEquipment);
        medicalEquipment.setCreateBy(UserHolder.getUserName());
        medicalEquipment.setUpdateBy(UserHolder.getUserName());
        medicalEquipmentMapper.saveOrUpdate(medicalEquipment);
        if (ObjectUtils.isEmpty(medicalEquipment.getId())) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "saveOrUpdate failed");
        }
        // 新增附件
        if (CollectionUtils.isNotEmpty(medicalEquipmentDto.getAddAnnex())) {
            List<MedicalEquipmentFile> files = medicalEquipmentDto.getAddAnnex().stream().map(x -> {
                MedicalEquipmentFile medicalEquipmentFile = new MedicalEquipmentFile();
                BeanUtils.copyProperties(x, medicalEquipmentFile);
                medicalEquipmentFile.setEquipmentId(medicalEquipment.getId());
                medicalEquipmentFile.setCreateBy(UserHolder.getUserName());
                medicalEquipmentFile.setUpdateBy(UserHolder.getUserName());
                return medicalEquipmentFile;
            }).collect(Collectors.toList());
            medicalequipmentfileService.saveBatch(files);
        }
        // 删除附件信息
        deleteMedicalEquipmentFiles(medicalEquipmentDto.getDeleteIds());
        return true;
    }

    private void deleteMedicalEquipmentFiles(List<Integer> deleteIds) {
        if (CollectionUtils.isEmpty(deleteIds)) {
            return;
        }
        List<MedicalEquipmentFile> files = medicalEquipmentFileMapper.selectBatchIds(deleteIds);
        medicalEquipmentFileMapper.update(null, Wrappers.<MedicalEquipmentFile>lambdaUpdate()
                .in(MedicalEquipmentFile::getId, deleteIds)
                .set(MedicalEquipmentFile::getIsDelete, BYConstant.INT_TRUE));
        minioUtils.removeFiles(minioPropertiesConfig.getBucketName(), files.stream().map(x -> x.getFilePath().replaceFirst(minioUtils.getBasisUrl(), "")).collect(Collectors.toList()));
    }

    @Override
    public MedicalEquipmentDetailVo getDetail(Integer id) {
        MedicalEquipment medicalEquipment = medicalEquipmentMapper.selectById(id);
        if (ObjectUtils.isEmpty(medicalEquipment)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "数据异常");
        }
        MedicalEquipmentDetailVo response = new MedicalEquipmentDetailVo();
        BeanUtils.copyProperties(medicalEquipment, response);
        List<MedicalEquipmentFile> annex = medicalEquipmentFileMapper.selectList(Wrappers.<MedicalEquipmentFile>lambdaQuery()
                .eq(MedicalEquipmentFile::getEquipmentId, id)
                .eq(MedicalEquipmentFile::getIsDelete, BYConstant.INT_FALSE));
        if (CollectionUtils.isNotEmpty(annex)) {
            response.setAnnex(Convert.toList(MedicalEquipmentFileVo.class, annex));
        }
        return response;
    }

    @Override
    public Boolean delete(Integer id) {
        // 删除附件
        List<MedicalEquipmentFile> files = medicalEquipmentFileMapper.selectList(Wrappers.<MedicalEquipmentFile>lambdaQuery().select(MedicalEquipmentFile::getId).eq(MedicalEquipmentFile::getEquipmentId, id));
        deleteMedicalEquipmentFiles(files.stream().map(MedicalEquipmentFile::getId).collect(Collectors.toList()));
        // 删除设备
        medicalEquipmentMapper.update(null, Wrappers.<MedicalEquipment>lambdaUpdate()
                .eq(MedicalEquipment::getId, id)
                .set(MedicalEquipment::getIsDelete, BYConstant.INT_TRUE));
        return true;
    }
}
