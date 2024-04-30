package com.parch.combine.core.tools.compare;

import com.parch.combine.common.util.CheckEmptyUtil;

import java.util.List;

/**
 * 比较处理器
 */
public class CompareHelper {

    /**
     * 比较
     *
     * @param config 测试配置
     */
    public static CompareResult compare(CompareConfig config) {
        // 根据类型执行不同的检测逻辑
        CompareResult result;
        CompareTypeEnum type = config.getCompareType();
        switch (type) {
            case CONTAINS:
                result = ContainCompare.compare(config);
                break;
            case NO_EMPTY:
            case IS_EMPTY:
            case NO_NULL:
            case IS_NULL:
                result = EmptyCompare.compare(config);
                break;
            case LTE:
            case LT:
            case GTE:
            case GT:
                result = QuantificationCompare.compare(config);
                break;
            case EQ:
            case NEQ:
                result = EquivalentCompare.compare(config);
                break;
            case IS_DATE:
            case IS_NUMBER:
            case IS_STRING:
            case IS_BOOLEAN:
            case IS_OBJECT:
            case IS_LIST:
                result = DataTypeCompare.compare(config);
                break;
            default:
                result = CompareResult.error("未知的比较类型");
        }

        return result;
    }

    /**
     * 判断条件是否通过
     *
     * @param config 条件组配置
     * @return 是否通过
     */
    public static boolean isPass(CompareGroupConfig config, boolean defaultIsPass) {
        List<CompareConfig> conditions = config.getConditions();
        String relationStr = config.getRelation();

        // 无条件配置，默认不通过
        if (CheckEmptyUtil.isEmpty(conditions)) {
            return defaultIsPass;
        }

        // 条件的逻辑关系（且|或）
        RelationEnum relation = RelationEnum.get(relationStr);

        // 执行逻辑判断
        if (relation == RelationEnum.AND) {
            // 全部满足才行
            for (CompareConfig condition : conditions) {
                CompareResult result = CompareHelper.compare(condition);
                if (!result.isSuccess()) {
                    return false;
                }
            }

            return true;
        } else {
            // 只满足一项即可
            for (CompareConfig condition : conditions) {
                CompareResult result = CompareHelper.compare(condition);
                if (result.isSuccess()) {
                    return true;
                }
            }

            return false;
        }
    }
}
