package com.parch.combine.core.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.HashMap;
import java.util.Map;

/**
 * json操作工具类，使用jackson替换fastjson相关操作
 */
public class JsonUtil {

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
        //simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        //simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        // 配置 ObjectMapper，使其在序列化时将日期以字符串形式输出，而不是以时间戳形式。
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 禁用在反序列化时遇到未知属性时抛出异常的功能，即允许实体类中存在多余的属性而不会导致反序列化失败
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    public static <T> String obj2String(T obj) {
        return SERIALIZER.obj2String(obj);
    }

    public static <T> String obj2StringPretty(T obj) {
        return SERIALIZER.obj2StringPretty(obj);
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        return SERIALIZER.string2Obj(str, clazz);
    }

    public static <T> T objToObj(Object fromValue, Class<T> toValueType){
        return SERIALIZER.objToObj(fromValue, toValueType);
    }

    /**
     * 在字符串与集合对象转换时使用
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        return SERIALIZER.string2Obj(str, typeReference);
    }

    /**
     * 在字符串与集合对象转换时使用
     */
    public static <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        return SERIALIZER.string2Obj(str, collectionClazz, elementClazzes);
    }

    public static <T, R extends JsonNode> R objToTree(T obj) {
        return SERIALIZER.objToTree(obj);
    }

    public static <T> T treeToObj(JsonNode jsonNode, Class<T> objClas) {
        return SERIALIZER.treeToObj(jsonNode, objClas);
    }

    //亮点：模拟构造方法设计模式提供类似于阿里巴巴FastJSON的put方式构造JSON功能
    public static JsonBuilder builder() {
        return new JsonBuilder();
    }

    public static class JsonBuilder {
        private Map<String ,Object> map = new HashMap<>();

        JsonBuilder() {
        }
        public JsonBuilder put(String key ,Object value){
            map.put(key,value);
            return this;
        }

        public String build() {

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(this.map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return "{}";
        }
    }
}

