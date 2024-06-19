package com.incaier.integration.platform.handler.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.fastjson.JSON;
import com.incaier.integration.platform.constant.BYConstant;
import com.incaier.integration.platform.handler.excel.valid.ExcelImportValid;
import com.incaier.integration.platform.handler.excel.valid.ExceptionCustom;
import com.incaier.integration.platform.mapper.SysRoleUserMapper;
import com.incaier.integration.platform.request.doctor.DoctorInfoDto;
import com.incaier.integration.platform.request.excel.ExcelDoctorEntity;
import com.incaier.integration.platform.service.DoctorInfoService;
import com.incaier.integration.platform.util.SpringContextUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * excel用户监听器
 *
 * @author caiweijie
 * @date 2024/06/17
 */
public class ExcelUserListener extends AnalysisEventListener<ExcelDoctorEntity> {

    private final Logger logger = LoggerFactory.getLogger(ExcelUserListener.class);

    private final List<ExcelDoctorEntity> list = new ArrayList<>();
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    /**
     * 医生信息服务
     */
    private final DoctorInfoService doctorInfoService;

    private final SysRoleUserMapper sysRoleUserMapper;

    private final HashMap<String, String> errorInsert = new HashMap<>();

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public ExcelUserListener() {
        doctorInfoService = SpringContextUtils.getBean(DoctorInfoService.class);
        sysRoleUserMapper = SpringContextUtils.getBean(SysRoleUserMapper.class);
    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(ExcelDoctorEntity goods, AnalysisContext analysisContext) {
        try {
            // 通用方法数据校验必填
            ExcelImportValid.valid(goods);
        } catch (ExceptionCustom e) {
            logger.error(e.getMessage());
            // 在 easyExcel 监听器中抛出业务异常
            throw new ExcelAnalysisException(e.getMessage());
        }
        // 数据存储到 datas，供批量处理，或后续自己业务逻辑处理。
        list.add(goods);
        // 达到 BATCH_COUNT，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 确保所有数据都能入库
        saveData();
        if (ObjectUtils.isNotEmpty(errorInsert)) {
            throw new ExcelAnalysisException(JSON.toJSONString(errorInsert));
        }
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
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
                errorInsert.put(doctor.getUserName(), e.getMessage());
            }
        });
    }
}