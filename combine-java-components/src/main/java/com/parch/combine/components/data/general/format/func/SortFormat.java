package com.parch.combine.components.data.general.format.func;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.tools.ValueTool;

import java.util.*;

/**
 * 集合排序
 */
public class SortFormat implements ICustomFormat {

    /**
     * 倒序标识
     */
    private final static String DESC = "desc";

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

        // 解析参数信息
        String[] keys = null;
        boolean isAsc = true;
        if (CheckEmptyUtil.isNotEmpty(params)) {
            String keyArrStr = params[0];
            if (CheckEmptyUtil.isNotEmpty(keyArrStr) && !keyArrStr.equals("-")) {
                keys = keyArrStr.split(KEY_SEPARATOR);
            }
            if (params.length > 1 && CheckEmptyUtil.isNotEmpty(params[1])) {
                isAsc = !DESC.equals(params[1].toLowerCase());
            }
        }

        // 排序
        List<Object> sortList = new ArrayList<>(list);
        ValueTool.sort(sortList, keys, !isAsc);
        return sortList;
    }
}
