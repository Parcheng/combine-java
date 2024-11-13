package com.parch.combine.core.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

/**
 * JSON序列化
 *
 * @author parch
 * @Date 2024/8/23
 */
public class JsonSerializer {

    private final ObjectMapper objectMapper;
    
    public JsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String obj2String(T obj) {
        if(obj == null){
            return null;
        }
        String s = null;
        try {
            s = obj instanceof String ? (String)obj :  objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public <T> T string2Obj(String str, Class<T> clazz) {
        if(str == null || str.length()==0 || clazz == null){
            return null;
        }
        T t = null;
        try {
            t = clazz.equals(String.class)? (T)str : objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public <T> T objToObj(Object fromValue, Class<T> toValueType){
        if(Objects.isNull(fromValue)){
            return null;
        }
        T t = null;
        try{
            t = objectMapper.convertValue(fromValue,toValueType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 在字符串与集合对象转换时使用
     */
    public <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (str ==null || str.length() ==0 || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在字符串与集合对象转换时使用
     */
    public <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转JsonNode
     *
     * @param obj
     * @return
     * @param <T>
     */
    public <T, R extends JsonNode> R objToTree(T obj) {
        try {
            // 将Java对象转换为JsonNode对象
            return objectMapper.valueToTree(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转对象
     *
     * @param jsonNode
     * @param objClas
     * @return
     * @param <T>
     */
    public <T> T treeToObj(JsonNode jsonNode, Class<T> objClas) {
        try {
            // 将JsonNode对象转换为Java对象
            return objectMapper.treeToValue(jsonNode, objClas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
