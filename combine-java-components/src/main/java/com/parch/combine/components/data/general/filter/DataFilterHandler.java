package com.parch.combine.components.data.general.filter;


import java.util.List;

/**
 * 数据过滤处理器
 */
public class DataFilterHandler {

    /**
     * 过滤
     *
     * @param config 配置
     * @return 结果
     */
    public static DataFilterErrorEnum filter(DataFilterLogicConfig.DataFilterItem config) {
        DataFilterRuleEnum rule = DataFilterRuleEnum.get(config.getRule());
        switch (rule) {
            case CLEAR:
                return clear(config.getFieldName());
            case REPLACE:
                return replace(config.getFieldName(), config.getRuleParams());
            default:
                return DataFilterErrorEnum.NONE_RULE;
        }
    }

    /**
     * 过滤 - 清理
     *
     * @param filedName 字段名
     * @return 结果
     */
    private static DataFilterErrorEnum clear(String filedName) {
        boolean success = DataVariableHelper.clearValue(filedName);
        return success ? null : DataFilterErrorEnum.CLEAR_ERROR;
    }

    /**
     * 过滤 - 替换
     *
     * @param filedName 字段名
     * @param params 参数
     * @return 结果
     */
    private static DataFilterErrorEnum replace(String filedName, List<String> params) {
        boolean success = DataVariableHelper.replaceValue(filedName, params, " ");
        return success ? null : DataFilterErrorEnum.REPLACE_ERROR;
    }
}
