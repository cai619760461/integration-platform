package com.incaier.integration.platform.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用序列化脱敏处理类
 */
public class SensitiveDataJsonSerializer {

    /**
     * 手机号序列化脱敏
     */
    public static class PhoneSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (!StringUtils.equals(value, "") && value.length() > 5) {
                String phone = value.substring(0, 3) + "****" + value.substring(value.length() - 4);
                gen.writeString(phone);
            } else {
                gen.writeString(value);
            }
        }
    }

    /**
     * 身份证号序列化脱敏
     */
    public static class IdCardSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (!StringUtils.equals(value, "") && value.length() > 10) {
                String idCard = value.substring(0, 6) + "********" + value.substring(value.length() - 4);
                gen.writeString(idCard);
            } else {
                gen.writeString(value);
            }
        }
    }

    /**
     * 姓名序列化脱敏
     */
    public static class NameSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (!StringUtils.equals(value, "")) {
                String name = value.charAt(0) + "**";
                gen.writeString(name);
            } else {
                gen.writeString(value);
            }
        }
    }

    /**
     * 性别序列化
     *
     * @author caiweijie
     * @date 2024/06/05
     */
    public static class GenderSerializer extends JsonSerializer<String> {

        private static final Map<String, String> GENDER_MAP = new HashMap<>();

        static {
            GENDER_MAP.put("1", "男");
            GENDER_MAP.put("2", "女");
            GENDER_MAP.put("9", "未知");
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(GENDER_MAP.getOrDefault(value, "未知"));
        }
    }
}
