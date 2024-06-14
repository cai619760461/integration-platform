package com.incaier.integration.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.mapper.PersonnelMapper;
import com.incaier.integration.platform.service.PersonnelService;
import org.springframework.stereotype.Service;

/**
 * 人员(Personnel)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 12:02:22
 */
@Service("personnelService")
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, Personnel> implements PersonnelService {


}