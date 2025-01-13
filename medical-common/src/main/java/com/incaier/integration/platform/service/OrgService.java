package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.Org;
import com.incaier.integration.platform.request.OrgDto;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.response.org.OrgVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构信息表服务层
 *
 * @description 机构信息表服务层
 * @author weijie.cai
 * @date 2024-06-13
 */
@Service
public interface OrgService extends IService<Org> {

    /**
     * 获取所有机构
     *
     * @param orgDto org dto
     * @return {@link Result}<{@link PageInfo}<{@link OrgVo}>>
     */
    PageInfo<OrgVo> getOrgList(OrgDto orgDto);

    /**
     * 保存或更新机构
     *
     * @param orgDto org dto
     * @return {@link Boolean}
     */
    Boolean saveOrUpdateOrg(OrgDto orgDto);

    /**
     * 获取所有列表
     *
     * @param orgDto org dto
     * @return {@link List }<{@link OrgVo }>
     */
    List<OrgVo> getAllList(OrgDto orgDto);
}