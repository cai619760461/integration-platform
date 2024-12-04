package com.incaier.integration.platform.handler.excel.listener;

import com.incaier.integration.platform.constant.BYConstant;
import com.incaier.integration.platform.mapper.SysRoleUserMapper;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.request.excel.ExcelDoctorEntity;
import com.incaier.integration.platform.service.DoctorInfoService;
import com.incaier.integration.platform.util.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * excel用户监听器
 *
 * @author caiweijie
 * @date 2024/06/17
 */
public class ExcelUserListener extends ExcelListener<ExcelDoctorEntity> {

    private final Logger logger = LoggerFactory.getLogger(ExcelUserListener.class);

    private final DoctorInfoService doctorInfoService;

    private final SysRoleUserMapper sysRoleUserMapper;

    public ExcelUserListener() {
        doctorInfoService = SpringContextUtils.getBean(DoctorInfoService.class);
        sysRoleUserMapper = SpringContextUtils.getBean(SysRoleUserMapper.class);
    }

    @Override
    public void checkData() {

    }

    /**
     * 加上存储数据库
     */
    @Override
    public void saveData() {
        list.forEach(doctor -> {
            try {
                DoctorInfoDto dto = new DoctorInfoDto();
                BeanUtils.copyProperties(doctor, dto);
                dto.setIsExpert(BYConstant.INT_FALSE);
                if (StringUtils.isNotEmpty(doctor.getRoleIds())) {
                    dto.setRoles(sysRoleUserMapper.getRolesByIds(doctor.getRoleIds().split(",")));
                }
                doctorInfoService.buildDoctorBasicInfo(dto);
            }catch (Exception e) {
                logger.error(e.getMessage());
                errorInsert.add(doctor.getUserName() + ":" + e.getMessage());
            }
        });
    }
}