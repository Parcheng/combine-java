package com.parch.combine.core.common.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * json操作工具类，使用jackson替换fastjson相关操作（序列化时忽略空值）
 */
public class ExcludeNullJsonUtil {

    private final static JsonSerializer SERIALIZER = new JsonSerializer(initMapper());

    /**
     * 初始化序列号Mapper（公共方法）
     */
    private static ObjectMapper initMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 注册 JavaTimeModule 模块到 ObjectMapper，以便正确处理 Java 8 中的日期和时间类。
        objectMapper.registerModule(new JavaTimeModule());
        // 注册 SimpleModule 模块到 ObjectMapper，以便自定义序列化和反序列化行为。
        objectMapper.registerModule(new SimpleModule());
        // 序列化时忽略空值
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 配置 ObjectMapper，使其在序列化时将日期以字符串形式输出，而不是以时间戳形式。
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 禁用在反序列化时遇到未知属性时抛出异常的功能，即允许实体类中存在多余的属性而不会导致反序列化失败
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    public static <T> String obj2String(T obj) {
        return SERIALIZER.obj2String(obj);
    }

    public static <T, R extends JsonNode> R objToTree(T obj) {
        return SERIALIZER.objToTree(obj);
    }
}

