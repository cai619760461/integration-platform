package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.SysDictType;
import com.incaier.integration.platform.request.SysDictTypeDto;
import com.incaier.integration.platform.response.dict.SysDictTypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 字典类型表Mapper
 * @author weijie.cai
 * @date 2024-06-12
 */
@Mapper
@DS("testMedicalManage")
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    /**
     * 获取字典类型列表
     *
     * @param dto dto
     * @return {@link List}<{@link SysDictTypeVo}>
     */
    List<SysDictTypeVo> getTypeList(SysDictTypeDto dto);

    /**
     * 保存或更新
     *
     * @param sysDictType entity
     * @return {@link Boolean}
     */
    Boolean saveOrUpdate(SysDictType sysDictType);
}