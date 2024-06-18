package com.incaier.integration.platform.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.handler.DataJsonSerializer;
import com.incaier.integration.platform.mapper.PersonnelMapper;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.service.PersonnelService;
import com.incaier.integration.platform.util.AesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * 人员(Personnel)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 12:02:22
 */
@Service("personnelService")
@DS("hipmaster")
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, Personnel> implements PersonnelService {

    @Value("${personnel.domainId}")
    private String domainId;

    @Override
    public Personnel savePersonnel(DoctorInfoDto doctorInfoDto) {
        Personnel newPersonnel = Personnel.builder()
                .domainId(domainId)
                .healthCareProviderId(doctorInfoDto.getUserName())
                .passWord(AesUtil.encrypt(doctorInfoDto.getUserName()))
                .phone(doctorInfoDto.getPhoneNumber())
                .identityNo(doctorInfoDto.getIdentityNo())
                .name(doctorInfoDto.getName())
                .genderId(doctorInfoDto.getSex().toString())
                .genderDepict(DataJsonSerializer.GenderSerializer.GENDER_MAP.getOrDefault(doctorInfoDto.getSex().toString(), "未知"))
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").format(doctorInfoDto.getBirthday()))
                .email(doctorInfoDto.getEmail())
                .createTime(LocalDateTime.now())
                .build();
        save(newPersonnel);
        return newPersonnel;
    }
}