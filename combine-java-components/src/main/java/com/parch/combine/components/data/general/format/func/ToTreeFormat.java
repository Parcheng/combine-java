package com.parch.combine.components.data.general.format.func;

import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合排序
 */
public class ToTreeFormat implements ICustomFormat {

    @Override
    public List<String> check(String[] params) {
        List<String> errorMsg = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(params) || params.length < 3) {
            errorMsg.add(FormatFuncError.PARAMS_COUNT_ERROR);
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

        String idField = params[0];
        String parentIdField = params[1];
        String childrenField = params[2];

        // 转Tree
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) sourceValue;
        Map<Object, Map<String, Object>> dataMap = dataList.stream().collect(Collectors.toMap(m -> m.get(idField), Function.identity()));
        for (Map<String, Object> item : dataList) {
            Object parentId = item.get(parentIdField);
            if (parentId == null || "".equals(parentId)) {
                result.add(item);
            } else {
                Map<String, Object> parentData = dataMap.get(parentId);
                if (parentData != null) {
                    Object children = parentData.get(childrenField);
                    if (!(children instanceof Collection)) {
                        children = new ArrayList<Map<String, Object>>();
                        parentData.put(childrenField, children);
                    }
                    ((Collection<Object>) children).add(item);
                }
            }
        }

        return result;
    }
}
