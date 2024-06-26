package com.parch.combine.core.component.tools.compare;

import com.parch.combine.core.component.tools.calc.ValueOptTool;

/**
 * 等值比较处理器
 */
public class EquivalentCompare {

    /**
     * 比较
     *
     * @param config 测试配置
     */
    public static CompareResult compare(CompareConfig config) {
        Object source = config.parseSourceValue();
        Object target = config.parseTargetValue();

        boolean isEquivalent;
        if (source == null && target == null) {
            isEquivalent = true;
        } else if (source == null || target == null) {
            isEquivalent = false;
        } else {
            isEquivalent = ValueOptTool.compareTo(source, target, null) == 0;
        }

        boolean success = (CompareTypeEnum.EQ == config.getCompareType()) == isEquivalent;
        return success ? CompareResult.success() : CompareResult.fail();
    }
}
