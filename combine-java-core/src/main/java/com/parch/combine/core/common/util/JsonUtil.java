package com.parch.combine.core.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.parch.combine.core.common.exception.CommonErrorEnum;
import com.parch.combine.core.common.exception.SysException;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * JSON转换工具类
 */
public class JsonUtil {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtil() {
    }

    public static <T> String serialize(T value, Class<T> clazz, JsonSerializer<T> serializer) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return (String)value;
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                SimpleModule module = new SimpleModule();
                module.addSerializer(clazz, serializer);
                mapper.registerModule(module);

                return mapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                throw new SysException(CommonErrorEnum.JSON_SERIALIZE_ERROR, e.getMessage());
            }
        }
    }

    public static <T> String serialize(T value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return (String)value;
        } else {
            try {
                return OBJECT_MAPPER.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                throw new SysException(CommonErrorEnum.JSON_SERIALIZE_ERROR, e.getMessage());
            }
        }
    }

    public static <T> T deserialize(String value, Class<T> valueClass) {
        if (value == null) {
            return null;
        } else {
            try {
                return OBJECT_MAPPER.readValue(value, valueClass);
            } catch (JsonProcessingException e) {
                throw new SysException(CommonErrorEnum.JSON_DESERIALIZE_ERROR, e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> parseArray(String jsonArrayStr, Class<T> valueClass) {
        if (jsonArrayStr == null) {
            return null;
        } else {
            try {
                return (List)OBJECT_MAPPER.readValue(jsonArrayStr, getGenericsType(List.class, valueClass));
            } catch (JsonProcessingException e) {
                throw new SysException(CommonErrorEnum.JSON_DESERIALIZE_ERROR, e.getMessage());
            }
        }
    }

    public static <T> T deserialize(String data, Class<T> clazz, Class<?>... elementClasses) {
        if (data == null) {
            return null;
        } else {
            try {
                return OBJECT_MAPPER.readValue(data, getGenericsType(clazz, elementClasses));
            } catch (IOException e) {
                throw new SysException(CommonErrorEnum.JSON_DESERIALIZE_ERROR, e.getMessage());
            }
        }
    }

    private static JavaType getGenericsType(Class<?> clazz, Class<?>... elementClasses) {
        try {
            return OBJECT_MAPPER.getTypeFactory().constructParametricType(clazz, elementClasses);
        } catch (Exception e) {
            throw new SysException(CommonErrorEnum.JSON_DESERIALIZE_ERROR, e.getMessage());
        }
    }

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new JsonUtil.LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonUtil.LocalDateTimeDeserializer());
        OBJECT_MAPPER.registerModule(javaTimeModule);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    private static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        private LocalDateTimeSerializer() {
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        }
    }

    private static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        private LocalDateTimeDeserializer() {
        }

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return Instant.ofEpochMilli(p.getLongValue()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        }
    }
}
