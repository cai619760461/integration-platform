package com.incaier.integration.platform.handler.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.incaier.integration.platform.handler.excel.valid.ExcelImportValid;
import com.incaier.integration.platform.handler.excel.valid.ExceptionCustom;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * excel用户监听器
 *
 * @author caiweijie
 * @date 2024/06/17
 */
public abstract class ExcelListener<T> extends AnalysisEventListener<T> {

    private final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    public final List<T> list = new ArrayList<>();

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;

    public final List<String> errorInsert = new ArrayList<>();

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public ExcelListener() {
//        doctorInfoService = SpringContextUtils.getBean(DoctorInfoService.class);
    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(T entities, AnalysisContext analysisContext) {
        try {
            // 通用方法数据校验必填
            ExcelImportValid.valid(entities);
        } catch (ExceptionCustom e) {
            logger.error(e.getMessage());
            // 在 easyExcel 监听器中抛出业务异常
            throw new ExcelAnalysisException(e.getMessage());
        }
        // 数据存储到 datas，供批量处理，或后续自己业务逻辑处理。
        list.add(entities);
        // 达到 BATCH_COUNT，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            // 校验参数
            checkData();
            // 保存数据
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
            throw new ExcelAnalysisException("失败" + errorInsert.size() + "条，" + String.join(";", errorInsert));
        }
    }

    /**
     * 加上存储数据库
     */
    public abstract void saveData();

    /**
     * 校验数据
     */
    public abstract void checkData();
}