package com.parch.combine.components.data.general.format.func;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JSON转换实现类
 */
public class JSONFormat implements ICustomFormat {

    @Override
    public List<String> check(List<String> params) {
        List<String> errorMsg = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(params)) {
            errorMsg.add(FormatFuncError.JSON_TYPE_IS_NULL);
            return errorMsg;
        }

        JSONFormatType type = JSONFormatType.get(params.get(0));
        if (type == JSONFormatType.NONE) {
            errorMsg.add(FormatFuncError.UNKNOWN_JSON_TYPE);
        }

        return errorMsg;
    }

    @Override
    public Object format(Object sourceValue, List<String> params) {
        if (sourceValue == null) {
            return null;
        }

        // 根据类型调用不同的JSON转换函数
        JSONFormatType type = JSONFormatType.get(params.get(0));
        switch (type) {
            case TO_JSON:
                return JsonUtil.serialize(sourceValue);
            case JSON_TO_LIST:
                return JsonUtil.parseArray(sourceValue.toString(), HashMap.class);
            case JSON_TO_OBJECT:
                return JsonUtil.deserialize(sourceValue.toString(), HashMap.class);
        }

        return null;
    }

    /**
     * 格式化类型
     */
    private enum JSONFormatType {
        TO_JSON, JSON_TO_LIST, JSON_TO_OBJECT, NONE;

        public static JSONFormatType get(String type) {
            if (CheckEmptyUtil.isEmpty(type)) {
                return NONE;
            }
            for (JSONFormatType value : JSONFormatType.values()) {
                if (value.toString().equals(type.toUpperCase())) {
                    return value;
                }
            }
            return NONE;
        }
    }
}
