package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.entity.SysDictData;
import com.incaier.integration.platform.mapper.SysDictDataMapper;
import com.incaier.integration.platform.request.SysDictDataDto;
import com.incaier.integration.platform.response.dict.SysDictDataVo;
import com.incaier.integration.platform.service.SysDictDataService;
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
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    private final Logger logger = LoggerFactory.getLogger(SysDictDataServiceImpl.class);

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public PageInfo<SysDictDataVo> getDataList(SysDictDataDto dto) {
        PageInfo<SysDictDataVo> pageInfo;
        Page<SysDictDataVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<SysDictDataVo> dictTypeList = sysDictDataMapper.getDataList(dto);
        pageInfo = page.toPageInfo();
        pageInfo.setList(dictTypeList);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public Boolean dataSaveOrUpdate(SysDictDataDto sysDictDataDto) {
        SysDictData sysDictData = new SysDictData();
        BeanUtils.copyProperties(sysDictDataDto, sysDictData);
        if (ObjectUtils.isEmpty(sysDictData.getId())) {
            sysDictData.setCreateBy(UserHolder.getUserName());
        }
        sysDictData.setUpdateBy(UserHolder.getUserName());
        return sysDictDataMapper.saveOrUpdate(sysDictData);
    }
}