package com.parch.combine.data.base.general.reset;

import com.parch.combine.core.component.tools.variable.DataVariableHelper;

/**
 * 数据过滤处理器
 */
public class DataResetHandler {

    /**
     * 重设值
     *
     * @param config 配置
     * @return 结果
     */
    public static DataResetErrorEnum reset(DataResetLogicConfig.DataResetConfig config, boolean nullValue) {
        Object value = config.value();
        if (value == null) {
            if (!nullValue) {
                return DataResetErrorEnum.NEW_VALUE_IS_NULL;
            }
        }

        // 设置新值
        boolean success = DataVariableHelper.replaceValue(config.target(), value, false);
        if (!success) {
            return DataResetErrorEnum.FAIL;
        }

        return null;
    }
}
