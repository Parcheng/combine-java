package com.parch.combine.core.component.tools.compare;

import com.parch.combine.core.common.util.DataTypeIsUtil;

import java.util.Date;

/**
 * 量化比较处理器
 */
public class QuantificationCompare {

    /**
     * 比较
     *
     * @param config 测试配置
     */
    public static CompareResult compare(CompareConfig config) {
        Object source = config.parseSourceValue();
        Object target = config.parseTargetValue();
        if (source == null || target == null) {
            return CompareResult.fail();
        }

        Double sourceNumber = null;
        if (DataTypeIsUtil.isNumber(source.toString())) {
            sourceNumber = Double.parseDouble(source.toString());
        } else if (source instanceof Date) {
            sourceNumber = (double) ((Date) source).getTime();
        }

        Double targetNumber = null;
        if (DataTypeIsUtil.isNumber(target.toString())) {
            targetNumber = Double.parseDouble(target.toString());
        } else if (target instanceof Date) {
            targetNumber = (double) ((Date) target).getTime();
        }
        
        if (sourceNumber == null || targetNumber == null) {
            return CompareResult.fail();
        }

        boolean success = false;
        switch (config.getCompareType()) {
            case GT:
                success = sourceNumber > targetNumber;
                break;
            case GTE:
                success = sourceNumber >= targetNumber;
                break;
            case LT:
                success = sourceNumber < targetNumber;
                break;
            case LTE:
                success = sourceNumber <= targetNumber;
                break;
        }

        return success ? CompareResult.success() : CompareResult.fail();
    }
}
