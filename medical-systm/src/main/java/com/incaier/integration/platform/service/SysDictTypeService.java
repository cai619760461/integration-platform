package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.SysDictType;
import com.incaier.integration.platform.request.SysDictTypeDto;
import com.incaier.integration.platform.response.dict.SysDictTypeVo;
import org.springframework.stereotype.Service;

/**
 * 字典类型表服务层
 *
 * @description 字典类型表服务层
 * @author weijie.cai
 * @date 2024-06-12
 */
@Service
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 获取字典类型列表
     *
     * @param sysDictTypeDto dto
     * @return {@link PageInfo}<{@link SysDictTypeVo}>
     */
    PageInfo<SysDictTypeVo> getTypeList(SysDictTypeDto sysDictTypeDto);

    /**
     * 新增字典类型
     *
     * @param sysDictTypeDto dto
     * @return {@link Boolean}
     */
    Boolean typeSaveOrUpdate(SysDictTypeDto sysDictTypeDto);

    /**
     * 类型删除
     *
     * @param id 类型id
     * @return {@link Boolean}
     */
    Boolean typeDelete(Integer id);
}