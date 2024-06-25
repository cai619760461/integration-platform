package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.Org;
import com.incaier.integration.platform.request.OrgDto;
import com.incaier.integration.platform.response.org.OrgVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 机构信息表Mapper
 *
 * @description 机构信息表Mapper
 * @author weijie.cai
 * @date 2024-06-13
 */
@Mapper
@DS("byIntegrationPlatform")
public interface OrgMapper extends BaseMapper<Org> {

    /**
     * 获取机构列表
     *
     * @param dto request
     * @return {@link List}<{@link OrgVo}>
     */
    List<OrgVo> getOrgList(OrgDto dto);

    /**
     * 保存或更新
     *
     * @param org org
     * @return {@link PageInfo}<{@link OrgVo}>
     */
    Boolean saveOrUpdate(Org org);

    /**
     * 获取机构计数
     *
     * @param dto request
     * @return {@link Integer}
     */
    Integer getOrgRecordCount(OrgDto dto);
}