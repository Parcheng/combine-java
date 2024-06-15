package com.parch.combine.components.data.general.format.func;

import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.*;

/**
 * 重复数据清理实现类
 */
public class ClearDuplicateFormat implements ICustomFormat {

    @Override
    public List<String> check(String[] params) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object format(Object sourceValue, String[] params) throws Exception {
        if (sourceValue == null) {
            return null;
        }

        if (!(sourceValue instanceof List)) {
            throw new Exception(FormatFuncError.DATA_TYPE_NOT_LIST);
        }

        List<Object> list = (List<Object>) sourceValue;
        if (CheckEmptyUtil.isEmpty(list)) {
            return new ArrayList<>();
        }

        // 判断是否重复,重复则删除
        Set<String> verifyData = new HashSet<>();
        if (CheckEmptyUtil.isNotEmpty(params) && list.get(0) instanceof Map) {
            List<Map<String, Object>> objList = (List<Map<String, Object>>) sourceValue;
            objList.removeIf(item -> {
                StringBuilder key = new StringBuilder();
                String[] paramKeys = params[0].split(KEY_SEPARATOR);
                for (String paramKey : paramKeys) {
                    key.append(item.get(paramKey));
                }
                if (verifyData.contains(key.toString())) {
                    return true;
                }
                verifyData.add(key.toString());
                return false;
            });
        } else {
            list.removeIf(item -> {
                if (verifyData.contains(item.toString())) {
                    return true;
                }
                verifyData.add(item.toString());
                return false;
            });
        }

        return list;
    }
}
