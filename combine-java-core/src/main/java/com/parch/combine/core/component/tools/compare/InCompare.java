package com.parch.combine.core.component.tools.compare;

import com.parch.combine.core.common.util.json.JsonUtil;
import java.util.Collection;
import java.util.Map;

/**
 * IN比较处理器
 */
public class InCompare {

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

        boolean success = false;
        String sourceJson = JsonUtil.obj2String(source);
        if (target instanceof Collection) {
            Collection<?> targetList = (Collection<?>) target;
            for (Object item : targetList) {
                if (equalsValue(sourceJson, item)) {
                    success = true;
                    break;
                }
            }
        } else if (target instanceof Map) {
            Map<?,?> targetMap = (Map<?,?>) target;
            for (Object item : targetMap.values()) {
                if (equalsValue(sourceJson, item)) {
                    success = true;
                    break;
                }
            }
        } else {
            String[] targetArr = target.toString().split(",");
            for (Object item : targetArr) {
                if (equalsValue(sourceJson, item)) {
                    success = true;
                    break;
                }
            }
        }

        return success ? CompareResult.success() : CompareResult.fail();
    }

    private static boolean equalsValue(String source, Object item) {
        return item != null && source.equals(item.toString());
    }
}
