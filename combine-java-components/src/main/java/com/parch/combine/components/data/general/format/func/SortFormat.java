package com.parch.combine.components.data.general.format.func;

import com.parch.combine.common.constant.CommonConstant;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.tools.ValueHelper;

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
    public List<String> check(List<String> params) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object format(Object sourceValue, List<String> params) throws Exception {
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
            String keyArrStr = params.get(0);
            if (CheckEmptyUtil.isNotEmpty(keyArrStr) && !keyArrStr.equals(CommonConstant.PLACEHOLDER)) {
                keys = keyArrStr.split(KEY_SEPARATOR);
            }
            if (params.size() > 1 && CheckEmptyUtil.isNotEmpty(params.get(1))) {
                isAsc = !DESC.equals(params.get(1).toLowerCase());
            }
        }

        // 排序
        List<Object> sortList = new ArrayList<>(list);
        ValueHelper.sort(sortList, keys, !isAsc);
        return sortList;
    }
}
