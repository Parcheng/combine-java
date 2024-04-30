package com.parch.combine.components.data.general.verify;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.tools.compare.CompareHelper;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import java.util.List;

/**
 * 数据过滤处理器
 */
public class DataVerifyHandler {

    /**
     * 验证
     *
     * @param config 配置
     * @return 结果
     */
    public static DataVerifyErrorEnum verify(DataVerifyLogicConfig.DataVerifyItem config, String defaultMsg, List<String> result) {
        Object configErrorMsg = DataVariableHelper.parseValue(config.getMsg(), false);
        String errorMsg = (defaultMsg == null ? CheckEmptyUtil.EMPTY : defaultMsg) + (configErrorMsg == null ? CheckEmptyUtil.EMPTY : configErrorMsg.toString());

        // 判断条件是否成立
        boolean isTrue = CompareHelper.isPass(config, false);
        if (!isTrue) {
            result.add(errorMsg);
        }

        return null;
    }
}
