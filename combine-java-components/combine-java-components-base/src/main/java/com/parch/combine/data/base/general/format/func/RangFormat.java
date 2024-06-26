package com.parch.combine.data.base.general.format.func;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 集合排序
 */
public class RangFormat implements ICustomFormat {

    @Override
    public List<String> check(String[] params) {
        List<String> errorMsg = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(params)) {
            errorMsg.add(FormatFuncError.PARAMS_IS_NULL);
            return errorMsg;
        }

        // 参数是否为数字类型校验
        if (!DataTypeIsUtil.isInteger(params[0])) {
            errorMsg.add(FormatFuncError.RAND_START_INDEX_IS_NOT_NUMBER);
        }
        if (params.length > 1 && !DataTypeIsUtil.isInteger(params[1])) {
            errorMsg.add(FormatFuncError.RAND_END_INDEX_IS_NOT_NUMBER);
        }

        return errorMsg;
    }

    @Override
    public Object format(Object sourceValue, String[] params) throws Exception {
        if (sourceValue == null) {
            return null;
        }

        int startIndex = Integer.parseInt(params[0]);
        if (sourceValue instanceof String) {
            String data = (String) sourceValue;
            int endIndex = params.length > 1 ? Integer.parseInt(params[1]) : data.length();
            return data.substring(startIndex, endIndex);
        } else if (sourceValue instanceof List) {
            List<?> dataList = (List<?>) sourceValue;
            int endIndex = params.length > 1 ? Integer.parseInt(params[1]) : dataList.size();
            return dataList.subList(startIndex, endIndex);
        } else {
            throw new Exception(FormatFuncError.DATA_TYPE_ERROR);
        }
    }
}
