package com.parch.combine.components.data.general.format.func;

import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合排序
 */
public class ToMapFormat implements ICustomFormat {

    @Override
    public List<String> check(String[] params) {
        List<String> errorMsg = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(params)) {
            errorMsg.add(FormatFuncError.GROUP_KEY_IS_NULL);
        }
        return errorMsg;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object format(Object sourceValue, String[] params) throws Exception {
        if (sourceValue == null) {
            return null;
        }

        // 验证数据类型
        if (!(sourceValue instanceof List)) {
            throw new Exception(FormatFuncError.DATA_TYPE_NOT_LIST);
        }
        List<?> list = (List<?>) sourceValue;
        if (CheckEmptyUtil.isEmpty(list)) {
            return new HashMap<String, Object>();
        }
        if (!(list.get(0) instanceof Map)) {
            throw new Exception(FormatFuncError.DATA_TYPE_NOT_OBJECT_LIST);
        }

        // 转Map
        String valueKey = params.length > 1 ? params[1] : null;
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) sourceValue;
        return dataList.stream().collect(Collectors.toMap(item -> {
            StringBuilder keys = new StringBuilder(CheckEmptyUtil.EMPTY);
            String[] paramKeys = params[0].split(KEY_SEPARATOR);
            for (String paramKey : paramKeys) {
                keys.append(item.get(paramKey));
            }
            return keys.toString();
        }, item -> valueKey == null ? Function.identity() : item.get(valueKey), (key1, key2) -> key2));
    }
}
