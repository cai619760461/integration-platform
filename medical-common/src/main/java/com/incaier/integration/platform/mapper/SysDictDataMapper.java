package com.incaier.integration.platform.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.incaier.integration.platform.entity.SysDictData;
import com.incaier.integration.platform.request.SysDictDataDto;
import com.incaier.integration.platform.response.dict.SysDictDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据表Mapper
 *
 * @description 字典数据表Mapper
 * @author weijie.cai
 * @date 2024-06-12
 */
@Mapper
@DS("testMedicalManage")
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    /**
     * 获取字典数据列表
     *
     * @param dto dto
     * @return {@link List}<{@link SysDictDataVo}>
     */
    List<SysDictDataVo> getDataList(SysDictDataDto dto);

    /**
     * 保存或更新
     *
     * @param sysDictData entity
     * @return {@link Boolean}
     */
    Boolean saveOrUpdate(SysDictData sysDictData);
}