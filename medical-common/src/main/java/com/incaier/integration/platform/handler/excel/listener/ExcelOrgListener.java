package com.incaier.integration.platform.handler.excel.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.entity.Org;
import com.incaier.integration.platform.handler.excel.valid.ExceptionCustom;
import com.incaier.integration.platform.request.excel.ExcelOrgEntity;
import com.incaier.integration.platform.service.OrgService;
import com.incaier.integration.platform.util.SpringContextUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * excel 机构 监听器
 *
 * @author caiweijie
 * @date 2024/06/17
 */
public class ExcelOrgListener extends ExcelListener<ExcelOrgEntity> {

    private final Logger logger = LoggerFactory.getLogger(ExcelOrgListener.class);

    private final OrgService orgService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public ExcelOrgListener() {
        orgService = SpringContextUtils.getBean(OrgService.class);
    }

    @Override
    public void checkData() {
        List<String> orgCodes = this.list.stream().map(ExcelOrgEntity::getCode).collect(Collectors.toList());
        List<Org> orgs = orgService.list(Wrappers.<Org>lambdaQuery().in(Org::getCode, orgCodes));
        if (CollectionUtils.isNotEmpty(orgs)) {
            orgCodes = orgs.stream().map(Org::getCode).collect(Collectors.toList());
            throw new ExceptionCustom("IMPORT_PARAM_CHECK_FAIL", "机构数据已存在：" + JSON.toJSONString(orgCodes));
        }
    }

    /**
     * 加上存储数据库
     */
    @Override
    public void saveData() {
        List<Org> result = this.list.stream().map(org -> {
            Org dto = new Org();
            dto.setCreateBy(UserHolder.getUserName());
            dto.setUpdateBy(UserHolder.getUserName());
            BeanUtils.copyProperties(org, dto);
            return dto;
        }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(result)) {
            orgService.saveBatch(result);
        }
    }
}