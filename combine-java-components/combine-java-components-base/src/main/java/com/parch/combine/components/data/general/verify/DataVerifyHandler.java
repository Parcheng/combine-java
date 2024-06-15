package com.parch.combine.components.data.general.verify;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.tools.compare.CompareTool;

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
        String errorMsg = (defaultMsg == null ? CheckEmptyUtil.EMPTY : defaultMsg) + config.msg();

        // 判断条件是否成立
        boolean isTrue = CompareTool.isPass(config.compare(), false);
        if (!isTrue) {
            result.add(errorMsg);
        }

        return null;
    }
}
