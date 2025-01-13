package com.incaier.integration.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.incaier.integration.platform.entity.doctor.DoctorInfo;
import com.incaier.integration.platform.request.doctor.DoctorDetailDto;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.request.doctor.DoctorQueryDto;
import com.incaier.integration.platform.request.excel.ExcelDoctorEntity;
import com.incaier.integration.platform.response.doctor.DoctorDetailVo;
import com.incaier.integration.platform.response.doctor.DoctorVo;

import java.util.List;

/**
 * <p>
 * 医师基本信息 服务类
 * </p>
 *
 * @author weijie.cai
 * @since 2024-06-13
 */
public interface DoctorInfoService extends IService<DoctorInfo> {

    /**
     * 获取医生列表
     *
     * @param queryDto dto
     * @return {@link PageInfo}<{@link DoctorVo}>
     */
    PageInfo<DoctorVo> getDoctorList(DoctorQueryDto queryDto);

    /**
     * 获取医生详细信息
     *
     * @param doctorId doctorId
     * @return {@link DoctorDetailVo}
     */
    DoctorDetailVo getDoctorDetail(Integer doctorId);

    /**
     * 添加医生
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean addDoctor(DoctorDetailDto dto);

    /**
     * 编辑详细信息
     *
     * @param dto request
     * @return {@link Boolean}
     */
    Boolean updateDetail(DoctorDetailDto dto);

    /**
     * 删除医生
     *
     * @param doctorId 医生id
     * @return {@link Boolean}
     */
    Boolean deleteDoctor(Integer doctorId);

    /**
     * 建立医生基本信息
     *
     * @param doctorInfoDto 医生信息dto
     * @return {@link Integer}
     */
    Integer buildDoctorBasicInfo(DoctorInfoDto doctorInfoDto);

    /**
     * 获取导入模板
     *
     * @return {@link List}<{@link ExcelDoctorEntity}>
     */
    List<ExcelDoctorEntity> getUserTemplate();

    /**
     * 通过医院代码和身份证号获取医生详细信息
     *
     * @param orgCode  医院代码
     * @param identityNo 身份号
     * @return {@link DoctorDetailVo }
     */
    DoctorDetailVo getDoctorDetailByCode(String orgCode, String identityNo);
}
