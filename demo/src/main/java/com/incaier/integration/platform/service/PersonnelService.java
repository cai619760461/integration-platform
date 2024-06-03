package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.incaier.integration.platform.entity.Personnel;
import com.github.pagehelper.PageInfo;

/**
 * 人员(Personnel)表服务接口
 *
 * @author makejava
 * @since 2023-04-26 12:02:22
 */
public interface PersonnelService extends IService<Personnel> {

    void resetPasswordToSm4() throws InterruptedException;

    PageInfo<Personnel> getPersonnelInfo(Integer id, Integer pageNum, Integer pageSize);
}