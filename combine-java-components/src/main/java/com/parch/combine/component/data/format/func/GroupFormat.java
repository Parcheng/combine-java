package com.parch.combine.component.data.format.func;

import com.parch.combine.common.util.CheckEmptyUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 集合排序
 */
public class GroupFormat implements ICustomFormat {

    @Override
    public List<String> check(List<String> params) {
        List<String> errorMsg = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(params)) {
            errorMsg.add(FormatFuncError.GROUP_KEY_IS_NULL);
        }
        return errorMsg;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object format(Object sourceValue, List<String> params) throws Exception {
        if (sourceValue == null) {
            return null;
        }

        // 验证数据类型
        if (!(sourceValue instanceof List)) {
            throw new Exception(FormatFuncError.DATA_TYPE_NOT_LIST);
        }
        List<?> list = (List<?>) sourceValue;
        if (CheckEmptyUtil.isEmpty(list)) {
            return new HashMap<String, List<?>>();
        }
        if (!(list.get(0) instanceof Map)) {
            throw new Exception(FormatFuncError.DATA_TYPE_NOT_OBJECT_LIST);
        }

        // 分组处理
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) sourceValue;
        return dataList.stream().collect(Collectors.groupingBy(item -> {
            StringBuilder keys = new StringBuilder(CheckEmptyUtil.EMPTY);
            String[] paramKeys = params.get(0).split(KEY_SEPARATOR);
            for (String paramKey : paramKeys) {
                keys.append(item.get(paramKey));
            }
            return keys.toString();
        }));
    }
}
