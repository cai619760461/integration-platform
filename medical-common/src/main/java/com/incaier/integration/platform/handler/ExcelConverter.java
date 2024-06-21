package com.incaier.integration.platform.handler;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.incaier.integration.platform.response.doctor.LabelVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Excel 字段转换器
 *
 * @author weijie.cai
 * @date 2024/06/06
 */
public class ExcelConverter {

    public static class IdCardConverter implements Converter<String> {
        @Override
        public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
            String value = context.getValue();
            if (!StringUtils.equals(value, "") && value.length() > 10) {
                value = value.substring(0, 6) + "********" + value.substring(value.length() - 4);
            }
            return new WriteCellData<>(value);
        }
    }

    public static class NameConverter implements Converter<String> {
        @Override
        public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
            String value = context.getValue();
            if (!StringUtils.equals(value, "")) {
                value = value.charAt(0) + "**";
            }
            return new WriteCellData<>(value);
        }
    }

    public static class GenderConverter implements Converter<Object> {
        private static final Map<Object, String> GENDER_MAP = new HashMap<>();

        static {
            GENDER_MAP.put("1", "男");
            GENDER_MAP.put(1, "男");
            GENDER_MAP.put("2", "女");
            GENDER_MAP.put(2, "女");
            GENDER_MAP.put("9", "未知");
            GENDER_MAP.put(9, "未知");
        }

        @Override
        public WriteCellData<?> convertToExcelData(WriteConverterContext<Object> context) {
            return new WriteCellData<>(GENDER_MAP.getOrDefault(context.getValue(), "未知"));
        }
    }

    /**
     * 启用转换器
     *
     * @author caiweijie
     * @date 2024/06/13
     */
    public static class EnableConverter implements Converter<Integer> {
        private static final Map<Integer, String> ENABLE_MAP = new HashMap<>();

        static {
            ENABLE_MAP.put(0, "是");
            ENABLE_MAP.put(1, "否");
        }

        @Override
        public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) {
            return new WriteCellData<>(ENABLE_MAP.getOrDefault(context.getValue(), "是"));
        }
    }

    /**
     * 本地日期字符串转换器
     *
     * @author caiweijie
     * @date 2024/06/17
     */
    public static class LocalDateStringConverter implements Converter<LocalDate> {
        @Override
        public Class<LocalDateTime> supportJavaTypeKey() {
            return LocalDateTime.class;
        }

        @Override
        public CellDataTypeEnum supportExcelTypeKey() {
            return CellDataTypeEnum.STRING;
        }

        @Override
        public WriteCellData<?> convertToExcelData(LocalDate localDate, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
            WriteCellData<String> cellData = new WriteCellData<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String cellValue;
            cellValue=formatter.format(localDate);
            cellData.setType(CellDataTypeEnum.STRING);
            cellData.setStringValue(cellValue);
            cellData.setData(cellValue);
            return cellData;
        }

        @Override
        public LocalDate convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
            return LocalDate.parse(cellData.getStringValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public static class LabelConverter implements Converter<List<LabelVo>> {
        @Override
        public WriteCellData<String> convertToExcelData(WriteConverterContext<List<LabelVo>> context) {
            List<LabelVo> labels = context.getValue();
            if (CollectionUtils.isNotEmpty(labels)) {
                return new WriteCellData<>(labels.stream().map(LabelVo::getDictLabel).collect(Collectors.joining(",")));
            }
            return new WriteCellData<>("");
        }
    }
}
