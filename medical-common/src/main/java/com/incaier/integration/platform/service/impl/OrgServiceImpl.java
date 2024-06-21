package com.incaier.integration.platform.service.impl;

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
import com.incaier.integration.platform.exception.CommonBusinessException;
import com.incaier.integration.platform.mapper.OrgMapper;
import com.incaier.integration.platform.request.OrgDto;
import com.incaier.integration.platform.response.org.OrgVo;
import com.incaier.integration.platform.service.OrgService;
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
 * 机构信息表服务层
 *
 * @author weijie.cai
 * @description 机构信息表服务层
 * @date 2024-06-13
 */
@Service
@DS("testMedicalManage")
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {

    private final Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public PageInfo<OrgVo> getOrgList(OrgDto dto) {
        PageInfo<OrgVo> pageInfo;
        Page<OrgVo> page = PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<OrgVo> orgList = orgMapper.getOrgList(dto);
        pageInfo = page.toPageInfo();
        pageInfo.setList(orgList);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public Boolean saveOrUpdateOrg(OrgDto orgDto) {
        if (ObjectUtils.isEmpty(orgDto.getId())) {
            Org org = orgMapper.selectOne(Wrappers.<Org>lambdaQuery()
                    .eq(Org::getCode, orgDto.getCode())
                    .eq(Org::getIsDelete, BYConstant.INT_FALSE)
                    .last(BYConstant.SQL_LIMIT_1));
            if (ObjectUtils.isNotEmpty(org)) {
                throw new CommonBusinessException(ErrorCodeConstant.COMMON_ERROR, "机构ID已存在");
            }
        }
        Org org = new Org();
        BeanUtils.copyProperties(orgDto, org);
        if (ObjectUtils.isEmpty(orgDto.getId())) {
            org.setCreateBy(UserHolder.getUserName());
        }
        org.setUpdateBy(UserHolder.getUserName());
        return orgMapper.saveOrUpdate(org);
    }
}