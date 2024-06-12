package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.SysDictData;
import com.incaier.integration.platform.request.SysDictDataDto;
import com.incaier.integration.platform.response.dict.SysDictDataVo;
import org.springframework.stereotype.Service;
/**
 * 字典数据表服务层
 *
 * @description 字典数据表服务层
 * @author weijie.cai
 * @date 2024-06-12
 */
@Service
public interface SysDictDataService extends IService<SysDictData> {

    /**
     * 获取字典数据列表
     *
     * @param sysDictDataDto dto
     * @return {@link PageInfo}<{@link SysDictDataVo}>
     */
    PageInfo<SysDictDataVo> getDataList(SysDictDataDto sysDictDataDto);

    /**
     * 数据保存或更新
     *
     * @param sysDictDataDto dto
     * @return {@link Boolean}
     */
    Boolean dataSaveOrUpdate(SysDictDataDto sysDictDataDto);
}