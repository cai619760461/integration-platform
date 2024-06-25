package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.constant.ErrorCodeConstant;
import com.incaier.integration.platform.entity.SysDictData;
import com.incaier.integration.platform.entity.SysDictType;
import com.incaier.integration.platform.exception.CommonBusinessException;
import com.incaier.integration.platform.mapper.SysDictDataMapper;
import com.incaier.integration.platform.mapper.SysDictTypeMapper;
import com.incaier.integration.platform.request.SysDictTypeDto;
import com.incaier.integration.platform.response.dict.SysDictTypeVo;
import com.incaier.integration.platform.service.SysDictTypeService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典类型
 *
 * @author weijie.cai
 * @description 用户电子健康卡变更日志
 * @date 2024-06-04
 */
@Service
@DS("byIntegrationPlatform")
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    private final Logger logger = LoggerFactory.getLogger(SysDictTypeServiceImpl.class);

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public PageInfo<SysDictTypeVo> getTypeList(SysDictTypeDto dto) {
        PageInfo<SysDictTypeVo> pageInfo;
        Page<SysDictTypeVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<SysDictTypeVo> dictTypeList = sysDictTypeMapper.getTypeList(dto);
        pageInfo = page.toPageInfo();
        pageInfo.setList(dictTypeList);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public Boolean typeSaveOrUpdate(SysDictTypeDto sysDictTypeDto) {
        SysDictType sysDictType = new SysDictType();
        BeanUtils.copyProperties(sysDictTypeDto, sysDictType);
        if (ObjectUtils.isEmpty(sysDictType.getId())) {
            sysDictType.setCreateBy(UserHolder.getUserName());
        }
        sysDictType.setUpdateBy(UserHolder.getUserName());
        return sysDictTypeMapper.saveOrUpdate(sysDictType);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public Boolean typeDelete(Integer id) {
        SysDictType sysDictType = sysDictTypeMapper.selectById(id);
        if (ObjectUtils.isEmpty(sysDictType)) {
            throw new CommonBusinessException(ErrorCodeConstant.COMMON_INVALID_PARAMETER, "数据异常");
        }
        int count = sysDictDataMapper.delete(Wrappers.<SysDictData>lambdaQuery().eq(SysDictData::getDictType, sysDictType.getDictType()));
        boolean flag = removeById(id);
        logger.info("删除字典类型：{},{}，删除字典数据：{}", id, flag, count);
        return true;
    }
}