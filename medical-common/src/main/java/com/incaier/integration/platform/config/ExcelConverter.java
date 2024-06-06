package com.incaier.integration.platform.config;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Excel 字段转换器
 *
 * @author william@StarImmortal
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

    public static class GenderConverter implements Converter<String> {
        private static final Map<String, String> GENDER_MAP = new HashMap<>();

        static {
            GENDER_MAP.put("1", "男");
            GENDER_MAP.put("2", "女");
            GENDER_MAP.put("9", "未知");
        }

        @Override
        public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) {
            return new WriteCellData<>(GENDER_MAP.getOrDefault(context.getValue(), "未知"));
        }
    }
}
