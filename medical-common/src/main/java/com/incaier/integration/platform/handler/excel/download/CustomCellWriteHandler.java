package com.incaier.integration.platform.handler.excel.download;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.incaier.integration.platform.annotation.ExcelValid;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义单元格写入处理程序
 *
 * @author caiweijie
 * @date 2024/11/20
 */
public class CustomCellWriteHandler implements CellWriteHandler {

    private final Map<Integer, Boolean> RED_HEADER_COLUMNS = new HashMap<>();

    /**
     * 构造方法，初始化需要标红的列
     *
     * @param clazz Excel实体类类型
     */
    public CustomCellWriteHandler(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelValid.class)) {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                if (excelProperty != null) {
                    String[] values = excelProperty.value();
                    if (values.length > 0) {
                        RED_HEADER_COLUMNS.put(findHeaderIndex(values[0], clazz), true);
                    }
                }
            }
        }
    }

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        context.getFirstCellData().setWriteCellStyle(null);
        Workbook workbook = context.getWriteSheetHolder().getSheet().getWorkbook();
        if (context.getHead()) {
            // 检查是否需要标红
            boolean isRedHeader = RED_HEADER_COLUMNS.getOrDefault(context.getHeadData().getColumnIndex(), false);
            CellStyle headStyle = isRedHeader ? createRedHeadStyle(workbook) : createDefaultHeadStyle(workbook);
            context.getCell().setCellStyle(headStyle);
        } else {
            // 普通内容样式
            CellStyle contentStyle = createContentStyle(workbook);
            context.getCell().setCellStyle(contentStyle);
        }
    }

    /**
     * 创建默认表头样式
     */
    private CellStyle createDefaultHeadStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        // 设置默认背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setWrapText(true);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 创建红色表头样式
     */
    private CellStyle createRedHeadStyle(Workbook workbook) {
        CellStyle style = createDefaultHeadStyle(workbook);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        font.setColor(IndexedColors.RED.getIndex());
        style.setFont(font);
        return style;
    }

    /**
     * 创建内容样式
     */
    private CellStyle createContentStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setWrapText(true);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }

    /**
     * 根据表头名称查找列索引
     *
     * @param headerName 表头名称
     * @param clazz      实体类类型
     * @return 列索引
     */
    private int findHeaderIndex(String headerName, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty != null && excelProperty.value()[0].equals(headerName)) {
                return i;
            }
        }
        return -1;
    }
}
