package com.incaier.integration.platform.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.incaier.integration.platform.handler.excel.download.CustomCellWriteHandler;
import com.incaier.integration.platform.request.PageDto;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static com.incaier.integration.platform.constant.BYConstant.*;

/**
 * Excel抽取工具类
 *
 * @author caiweijie
 * @date 2024/06/13
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 分批数据导出 EXCEL
     * 单表最多 500000、每 250000 写入一次
     * 传入列表请求入参、自定义总数函数、自定义分批查询函数、转化表格字段类
     * 注意，主键自增且列表 id 正序
     *
     * @param dto              请求入参
     * @param response         响应体
     * @param getCountFunction 获取计数函数
     * @param getDataFunction  获取数据函数
     * @param dataClass        数据类
     */
    public static <T, D extends PageDto> void export(D dto, HttpServletResponse response,
                                                     Function<D, Integer> getCountFunction,
                                                     Function<D, List<T>> getDataFunction,
                                                     Class<T> dataClass) {
        logger.info("************* 列表导出 EXCEL 开始，操作行为：{} **************", JSON.toJSONString(dto));
        // 文件名
        String fileName = "Export_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        try (OutputStream outputStream = response.getOutputStream()) {
            // 获取导出总数
            int count = getCountFunction.apply(dto);
            // 定义首表、单次任务插入条数、获取分页总数
            int sheetNo = 1, pageCount = count % EXCEL_INSERT_NUM == 0 ? count / EXCEL_INSERT_NUM : count / EXCEL_INSERT_NUM + 1;
            // 初始化 ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(outputStream)
                    .head(dataClass)
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();
            // 创建首表 WriteSheet
            String sheetName = EXCEL_SHEET_PRFIX + sheetNo;
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, sheetName).build();
            // 记录上一次任务最大 id
            Long lastId = null;
            // 写入每一页分页查询的数据
            for (int i = 1; i <= pageCount; i++) {
                // 首页直接进行分页查询，反之基于上一次分页查询的分页定位实际偏移量，筛选前n条数据
                PageHelper.startPage(i == 1 ? i : 0, EXCEL_INSERT_NUM, false);
                dto.setLastId(lastId);
                List<T> records = getDataFunction.apply(dto);
                // 更新下一次分页查询用的id
                if (CollectionUtils.isNotEmpty(records)) {
                    try {
                        Object idValue;
                        T record = records.get(records.size() - 1);
                        Class<?> clazz = record.getClass();
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            if(Arrays.asList("id", "pk").contains(field.getName())){
                                field.setAccessible(true);
                                idValue = field.get(record);
                                if (idValue instanceof Number) {
                                    lastId = ((Number) idValue).longValue();
                                } else {
                                    throw new RuntimeException("Unsupported id field type");
                                }
                                break;
                            }
                        }
                    } catch (IllegalAccessException | RuntimeException e) {
                        logger.error(e.getMessage());
                    }
                }
                // 写入表格
                excelWriter.write(records, writeSheet);
                // 是否达到单表最大数据量，达到切换新表
                if ((i * EXCEL_INSERT_NUM) % EXCEL_SHEET_MAX == 0) {
                    sheetName = EXCEL_SHEET_PRFIX + (++sheetNo);
                    writeSheet = EasyExcel.writerSheet(sheetNo, sheetName).build();
                }
            }
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            excelWriter.finish();
            outputStream.flush();
            outputStream.close();
            logger.info("************* EXCEL 导出结束 **************");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 模板下载
     *
     * @param response 回答
     * @param t        t
     * @param list     列表
     * @throws Exception 异常
     */
    public static <T, F> void download(String name, HttpServletResponse response, Class<T> t, List<F> list) throws Exception {
        String fileName = URLEncoder.encode( name + ".xlsx", "utf-8");
        // 设置文本内省
        response.setContentType("application/vnd.ms-excel");
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置响应头
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        // 内容样式策略 已有策略 全局
//        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//        // 垂直居中,水平居中
//        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
//        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
//        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
//        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
//        // 设置 自动换行
//        contentWriteCellStyle.setWrapped(true);
//        // 字体策略
//        WriteFont contentWriteFont = new WriteFont();
//        // 字体大小
//        contentWriteFont.setFontHeightInPoints((short) 12);
//        contentWriteCellStyle.setWriteFont(contentWriteFont);
//        // 头策略使用默认 设置字体大小
//        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        WriteFont headWriteFont = new WriteFont();
//        headWriteFont.setFontHeightInPoints((short) 12);
//        headWriteCellStyle.setWriteFont(headWriteFont);
        // io流写入数据
        ServletOutputStream outputStream = response.getOutputStream();
        EasyExcel.write(outputStream, t)
//                .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle))
                .registerWriteHandler(new CustomCellWriteHandler(t))
                .sheet("模板")
                .doWrite(list);
        outputStream.flush();
        outputStream.close();
    }
}
