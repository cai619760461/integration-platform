package com.incaier.integration.platform.handler;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据自定义序列化
 *
 * @author caiweijie
 * @date 2024/06/14
 */
public class DataJsonSerializer {

    /**
     * 性别序列化
     *
     * @author caiweijie
     * @date 2024/06/05
     */
    public static class GenderSerializer extends JsonSerializer<Object> {

        public static final Map<String, String> GENDER_MAP = new HashMap<>();

        static {
            GENDER_MAP.put("1", "男");
            GENDER_MAP.put("2", "女");
            GENDER_MAP.put("9", "未知");
        }

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(GENDER_MAP.getOrDefault(String.valueOf(value), "未知"));
        }
    }
}
